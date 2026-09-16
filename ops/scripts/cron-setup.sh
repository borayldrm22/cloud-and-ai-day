#!/usr/bin/env bash
# Creates the "Morning DevOps Digest" scheduled job (Demo Part 3).
# The digest lands as a dashboard notification — no Slack required.
set -euo pipefail

kirocrew cron add \
  --name "Morning DevOps Digest" \
  --cron "0 9 * * 1-5" \
  --timezone "Europe/Istanbul" \
  --minimal-context \
  --no-persistent-session \
  --message "You are a DevOps digest agent. Produce the morning briefing for the team.

Repository: borayldrm22/cloud-and-ai-day

1. Stale PRs — follow ops/playbooks/stale-pr-playbook.md. Use the gh CLI against
   this repository. Report each as: PR #NN — <title> — N days open.

2. CloudWatch alarms — list alarms that changed state in the last 24 hours.
   aws cloudwatch describe-alarm-history --max-records 20
   Mark each as auto-resolved or still open.

3. Cost check — report any service with a day-over-day spend increase above 50%.
   If Cost Explorer is not available in this account, say so plainly instead of
   inventing a number.

4. Compose the digest in exactly this shape:

🌅 DevOps Morning Digest — <today>

🔴 Cost anomaly    (omit the line if none)
⚠️  Open alarms     (omit the line if none)
📋 Stale PRs
✅ Auto-resolved in the last 24h

Write '✅ All clear' for any empty category. Keep the whole digest under
12 lines. End with exactly one recommended action for today.

Send the result as a dashboard notification titled: ☀️ Morning DevOps Digest"

echo ""
echo "✅ Cron created. List jobs with:"
echo "   kirocrew cron list"
echo ""
echo "Trigger it manually for the recording:"
echo "   kirocrew cron trigger <job-id>"
