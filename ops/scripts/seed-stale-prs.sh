#!/usr/bin/env bash
# Opens a few genuinely old pull requests so the morning digest has real
# data to find (Demo Part 3, "Path A"). Run once, well before recording.
#
# Requires: gh CLI authenticated against this repository.
set -euo pipefail

BASE="${BASE:-main}"
DAYS_AGO="${DAYS_AGO:-9}"

seed() {
  local branch="$1" file="$2" title="$3" body="$4"
  local when
  when="$(date -u -d "${DAYS_AGO} days ago" +%Y-%m-%dT%H:%M:%SZ 2>/dev/null \
        || date -u -v-"${DAYS_AGO}"d +%Y-%m-%dT%H:%M:%SZ)"

  git checkout -b "$branch" "$BASE"
  printf '%s\n' "$body" > "$file"
  git add "$file"
  GIT_AUTHOR_DATE="$when" GIT_COMMITTER_DATE="$when" \
    git commit -m "$title"
  git push -u origin "$branch"
  gh pr create --base "$BASE" --head "$branch" --title "$title" --body "$body"
  git checkout "$BASE"
}

seed "chore/bump-logging-config" \
     "docs/notes-logging.md" \
     "chore: tune logging levels for the user service" \
     "Drops UserService to DEBUG in non-prod. Needs a second pair of eyes."

seed "docs/api-notes" \
     "docs/notes-api.md" \
     "docs: draft notes for the users endpoint" \
     "Rough notes before the endpoint is finalised."

seed "chore/cleanup-unused-imports" \
     "docs/notes-cleanup.md" \
     "chore: remove unused imports" \
     "Mechanical cleanup, no behaviour change."

echo ""
echo "✅ Seed PRs opened. Leave them unreviewed."
echo "   Check with: gh pr list --state open"
