# High CPU Incident Playbook

## Scope
This playbook is for the Cloud and AI Day demo environment only.
Do not change production resources.

## Trigger
Investigate when the CloudWatch alarm `demo-high-cpu` is in the ALARM state.

## Investigation steps
1. Read the current state, threshold, metric value, and timestamp of the alarm.
2. Identify the affected metric namespace and metric name.
3. Check whether the alarm is a demo metric or a production workload.
4. For this demo, classify the incident as `DEMO / NO PRODUCTION IMPACT`.

## Decision policy
- Do not terminate, restart, or scale any AWS resource.
- Do not modify Auto Scaling groups, ECS services, EC2 instances, or Lambda configuration.
- If the affected metric is `DemoMetrics / CPUUtilization`, recommend monitoring only.
- Escalate if the alarm points to a non-demo namespace.

## Report format
Return a concise incident report with:
- Incident title
- Alarm state and observed value
- Affected metric
- Root-cause assessment
- Action taken
- Production impact
- Follow-up recommendation
