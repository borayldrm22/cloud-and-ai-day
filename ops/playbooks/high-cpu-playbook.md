# High CPU Playbook

Runbook the agent reads when `HighCPUAlarm` fires. Demo Part 2 depends on
this file being present on `main`.

## Trigger

| Field  | Value                    |
|--------|--------------------------|
| Metric | `CPUUtilization`         |
| Alarm  | `HighCPUAlarm`           |
| Namespace | `DemoMetrics`         |
| Threshold | `> 90%` for 5 minutes |

## Steps

1. Read the current CloudWatch metric and confirm the alarm is not flapping.
   ```
   aws cloudwatch get-metric-statistics \
     --namespace DemoMetrics \
     --metric-name CPUUtilization \
     --start-time "$(date -u -d '15 minutes ago' +%Y-%m-%dT%H:%M:%SZ)" \
     --end-time "$(date -u +%Y-%m-%dT%H:%M:%SZ)" \
     --period 60 \
     --statistics Average
   ```
2. Identify the workload driving the load (`top`, `ps aux --sort=-%cpu`).
   In the demo environment this step is reported, not executed.
3. Scale the auto scaling group out by one instance.
   ```
   aws autoscaling set-desired-capacity \
     --auto-scaling-group-name demo-asg \
     --desired-capacity 2
   ```
4. Wait 5 minutes and re-check the metric.
5. If CPU is still above threshold, restart the service on the hot instance.
6. Post a summary to the dashboard: cause, action taken, time to resolve.

## Rollback

- Set the auto scaling group desired capacity back to `1`.
- Reset the alarm state to `OK`:
  ```
  aws cloudwatch set-alarm-state \
    --alarm-name HighCPUAlarm \
    --state-value OK \
    --state-reason "Demo reset"
  ```

## Guardrails

- Never scale beyond 4 instances without a human approval gate.
- Never terminate an instance from this playbook — restart only.
- Every action taken must land in the run log.
