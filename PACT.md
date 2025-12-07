---
version: 2.0
date: 2025-10-20
title: FullCalendar Pro Rules Adoption Pact
project: JWebMP / FullCalendar Pro
authors: [JWebMP Maintainers, Codex]
---

# 🤝 FullCalendar Pro Pact

## 1. Purpose
- Align every artifact with the Rules Repository (rules/ submodule) and this host project’s minimal codebase (Java 25, Maven, GuicedEE services, TypeScript client wiring).
- Treat documentation as the first, living deliverable: architectures, glossaries, rules, guides, and implementation notes describe the observed repository before code changes are considered.
- Close the loop between `PACT.md`, `GLOSSARY.md`, `RULES.md`, `GUIDES.md`, `IMPLEMENTATION.md`, and the architecture sources under `docs/architecture/`.

## 2. Principles
- **Forward-only**: No reintroductions of legacy docs; remove or replace outdated material and update every reference within the same change set.
- **Document modular**: Keep each doc focused, reference indexes, and minimize duplication by linking back to the relevant rules and glossaries in `rules/`.
- **Stage-gated discipline**: Stage 1 was auto-approved via the supplied blanket approval; future stages remain gated when approvals are enforced.
- **Spec-driven, TDD-first**: Build flows, behaviors, and acceptance criteria are expressed in docs before any scaffolding or code touches the runtime.
- **Logging standard**: Prefer Log4j2 (with Lombok `@Log4j2` when annotations are used) and route configuration through `rules/generative/backend/logging/LOGGING_RULES.md`.

## 3. Structure
- **PACT** ↔ cross-link to this file.
- **Glossary-first** (topic priority) with `GLOSSARY.md` pointing into `rules/` glossaries.
- **Rules** declare scopes and reference topic indexes (JWebMP, GuicedEE, Logging, JSpecify, Angular 20, GitHub Actions, etc.).
- **Guides** describe how to apply those rules: packaging, runtime wiring, testing, CI, and environment secrets.
- **Implementation** notes the actual repository structure, modules, and service registrations.
- **Architecture artifacts** (C4, sequences, ERD) live under `docs/architecture/`; each doc is mermaid/ textual and linked everywhere.

## 4. Collaboration agreement
- Every stage’s outputs mention whether the gate was auto-approved or if explicit approval is pending (per Stage Gate Interaction Protocol).
- Each new doc lists the relevant `rules/` file anchors to facilitate navigable traceability.
- When prompts reference Frontend components, they should anchor to the nearest topic glossary before writing slipstream copy.

## 5. Evidence and Traceability
- Architecture diagrams were created with Mermaid (hosted via Mermaid MCP: `https://mcp.mermaidchart.com/mcp`) and linked from `docs/architecture/README.md`.
- Glossary, rules, guides, implementation, and README all reference `docs/architecture/` to keep the documentation loop closed.
- Environment variables follow `rules/generative/platform/secrets-config/env-variables.md`; `.env.example` mirrors the vetted keys.
