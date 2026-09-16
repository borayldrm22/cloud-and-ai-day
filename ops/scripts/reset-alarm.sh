#!/usr/bin/env bash
# Puts the demo environment back to a clean state.
# Run between takes so the next recording starts from OK.
set -euo pipefail

ALARM_NAME="${ALARM_NAME:-HighCPUAlarm}"
ASG_NAME="${ASG_NAME:-}"

aws cloudwatch set-alarm-state \
  --alarm-name "$ALARM_NAME" \
  --state-value OK \
  --state-reason "Demo reset between takes"

if [[ -n "$ASG_NAME" ]]; then
  aws autoscaling set-desired-capacity \
    --auto-scaling-group-name "$ASG_NAME" \
    --desired-capacity 1
  echo "→ ASG '$ASG_NAME' scaled back to 1"
fi

echo "✅ Reset done. Alarm '$ALARM_NAME' is OK."
