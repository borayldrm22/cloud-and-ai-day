#!/usr/bin/env bash
# Pushes a high CPU datapoint and forces the alarm into ALARM state.
# This is the on-camera command for Demo Part 2, step 2.
set -euo pipefail

ALARM_NAME="${ALARM_NAME:-HighCPUAlarm}"
NAMESPACE="${NAMESPACE:-DemoMetrics}"
VALUE="${VALUE:-95}"

echo "→ Publishing CPUUtilization=${VALUE}% to ${NAMESPACE}"
aws cloudwatch put-metric-data \
  --namespace "$NAMESPACE" \
  --metric-name CPUUtilization \
  --value "$VALUE" \
  --unit Percent

echo "→ Forcing ${ALARM_NAME} into ALARM"
aws cloudwatch set-alarm-state \
  --alarm-name "$ALARM_NAME" \
  --state-value ALARM \
  --state-reason "Demo: simulated high CPU (${VALUE}%)"

echo "✅ Alarm is firing. Trigger the agent now."
