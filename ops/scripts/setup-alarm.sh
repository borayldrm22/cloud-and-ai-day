#!/usr/bin/env bash
# Creates the demo CloudWatch alarm used by Demo Part 2.
# Run once before recording. Requires: aws CLI, configured credentials.
set -euo pipefail

ALARM_NAME="${ALARM_NAME:-HighCPUAlarm}"
NAMESPACE="${NAMESPACE:-DemoMetrics}"
SNS_TOPIC_ARN="${SNS_TOPIC_ARN:-}"

args=(
  --alarm-name "$ALARM_NAME"
  --namespace "$NAMESPACE"
  --metric-name CPUUtilization
  --statistic Average
  --period 60
  --evaluation-periods 1
  --threshold 90
  --comparison-operator GreaterThanThreshold
  --alarm-description "Demo: CPU > 90%"
  --treat-missing-data notBreaching
)

if [[ -n "$SNS_TOPIC_ARN" ]]; then
  args+=(--alarm-actions "$SNS_TOPIC_ARN")
else
  echo "! SNS_TOPIC_ARN not set — alarm will have no action attached."
  echo "  Fine for the demo: the agent is triggered manually."
fi

aws cloudwatch put-metric-alarm "${args[@]}"

echo "✅ Alarm '$ALARM_NAME' created in namespace '$NAMESPACE'."
echo "   Verify: aws cloudwatch describe-alarms --alarm-names $ALARM_NAME"
