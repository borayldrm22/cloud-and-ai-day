## Cloud and AI Day Türkiye 2026

### Presentation resources
- Kiro Crew: https://kiro.dev/crew/
- Introducing Kiro Crew: https://kiro.dev/blog/introducing-kiro-crew/
- Kiro Crew GitHub: https://github.com/kirodotdev/KiroCrew

### Speaker
- LinkedIn: https://www.linkedin.com/in/bora-yıldırım-692404214/
# cloud-and-ai-day

Demo environment for the talk **"From CI/CD to Agentic DevOps: Automating
Cloud Ops with Kiro Crew"** — AWS Cloud & AI Day Türkiye, Istanbul,
23 September 2026.

Everything the three demo scenarios need lives in this repository: a small
Spring Boot service to break and fix, the runbooks the agent reads, and the
scripts that drive the alarm and the scheduled digest.

## What's here

```
src/                     Spring Boot service — the thing that breaks
.github/workflows/ci.yml Build + test on every PR
ops/playbooks/           Runbooks the agent reads and executes
ops/scripts/             Alarm setup, simulation, cron job, PR seeding
docs/                    Per-scenario recording plans
```

## The service

A deliberately small user API. Three seeded users, two endpoints, real
tests. It exists so there is something honest to break.

```bash
mvn -B verify          # build and test
mvn spring-boot:run    # http://localhost:8080/api/users
```

| Endpoint                 | Description                     |
|--------------------------|---------------------------------|
| `GET /api/users`         | every user                      |
| `GET /api/users/stats`   | totals and active count         |
| `GET /api/users/{id}/profile` | public profile projection  |

## The three scenarios

| # | Scenario            | What the agent does                                    | Plan |
|---|---------------------|--------------------------------------------------------|------|
| 1 | PR babysitting      | Watches a PR, reads the failing CI log, writes the fix, pushes | [docs/demo-01-pr-babysit.md](docs/demo-01-pr-babysit.md) |
| 2 | Night alarm handler | Picks up a CloudWatch alarm, reads the runbook, acts   | [docs/demo-02-alarm-handler.md](docs/demo-02-alarm-handler.md) |
| 3 | Morning digest      | Scheduled scan of PRs, alarms and cost; posts a digest | [docs/demo-03-morning-digest.md](docs/demo-03-morning-digest.md) |

Recording order matters: scenario 2 before scenario 3, so the alarm
resolved at night shows up in the morning digest.

## Branches

| Branch                   | Purpose                                              |
|--------------------------|------------------------------------------------------|
| `main`                   | Green. CI passes.                                    |
| `bug/user-profile-npe`   | Adds a profile endpoint with a missing null check. Open a PR from this branch to start scenario 1. |

The bug is real, not staged output: `UserService.buildProfile` dereferences
a user the repository never found, and the test that covers the 404 path
fails with a `NullPointerException`.

## Guardrails

The runbooks in `ops/playbooks/` state their own limits — read-only where it
matters, no instance termination, human approval above a threshold. They are
written that way on purpose: the point of the talk is that agents get scoped
authority, not unlimited authority.
