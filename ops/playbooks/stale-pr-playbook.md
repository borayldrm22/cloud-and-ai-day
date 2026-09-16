# Stale PR Playbook

Runbook used by the morning digest job (Demo Part 3).

## Definition

A pull request is **stale** when all of the following hold:

- open for more than 7 days
- no reviewer assigned, or no review activity in the last 7 days
- not marked `draft`
- not labelled `blocked` or `on-hold`

## Steps

1. List candidates.
   ```
   gh pr list --state open --json number,title,createdAt,reviewRequests,isDraft,labels
   ```
2. Drop drafts and anything labelled `blocked` / `on-hold`.
3. For each remaining PR, report: number, title, age in days, whether CI is green.
4. Do **not** close, comment on, or modify the PR. Report only.
5. Include the list in the morning digest under `Stale PRs`.

## Guardrails

- This playbook is read-only. Any write action needs a human approval gate.
- If more than 10 PRs qualify, report the 5 oldest and note the total count.
