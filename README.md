# JWebMP FullCalendar Pro

This module packages the **FullCalendar Pro** extension for JWebMP. The artifact (`com.jwebmp.plugins:full-calendar-pro`) wires into the GuicedEE runtime, registers page configurators, and packages the TypeScript client models that drive the FullCalendar widgets.

## Rules Repository Adoption
- The `rules/` submodule (https://github.com/GuicedEE/ai-rules.git) is the source for canonical policies. This repository honors the stage-gated workflow and forward-only/document-modularity policies enforced by the Rules Repository.
- Essential host docs (outside the submodule) include:
  - [`PACT.md`](PACT.md) — Collaboration agreement plus stage references.
  - [`GLOSSARY.md`](GLOSSARY.md) — Topic-first glossary pointing into the rules hierarchy.
  - [`RULES.md`](RULES.md) — Scope, stacks, logging, JSpecify, CI, and architectural guidelines.
  - [`GUIDES.md`](GUIDES.md) — How-to steps for packaging, runtime wiring, testing, and CI/secrets.
  - [`IMPLEMENTATION.md`](IMPLEMENTATION.md) — Observable implementation layout, service descriptors, and directories.
  - [`docs/architecture/README.md`](docs/architecture/README.md) — C4, sequences, and ERD sources (Mermaid-based) produced using the Mermaid MCP server.
  - [`docs/PROMPT_REFERENCE.md`](docs/PROMPT_REFERENCE.md) — Stack selections and diagram links for future prompts.
  - [`rules/generative/frontend/jwebmp/fullcalendar-pro/README.md`](rules/generative/frontend/jwebmp/fullcalendar-pro/README.md) — Topic index for the FullCalendar Pro rules with component-level guidelines, usage sketches, and release notes that keep the host implementation aligned with the Rules Repository.

## Environment & CI
- `.env.example` documents the keys that map to `rules/generative/platform/secrets-config/env-variables.md` and to GitHub workflows or runtime configs.
- GitHub Actions workflow: `.github/workflows/maven-build.yml` runs `mvn -B -V verify` on pushes and pull requests targeting `main/master` using Temurin JDK 25.

## Architecture Overview
- See `docs/architecture/README.md` for the C4 context, container, and component diagrams plus the packaging/runtime sequence flows and the ERD that describe the plugin’s bounded context.

## Implementation Notes
- `src/main/resources/META-INF/services` declares `FullCalendarProModuleScanInclusion` (GuicedEE module inclusion) and `FullCalendarProPageConfigurator` (page wiring).
- Modular documentation ensures that any new service or asset also describes the architecture/diagrams (`docs/architecture/*`), the glossary term, and how it satisfies the rules/guides/implementation loop.
- Stage 3 plans live in [`docs/implementation-plan.md`](docs/implementation-plan.md), so any future scaffolding aligns with the documented rollout steps, CI/emissions, and risk mitigation checklist.

## How to use these rules
- Start with the topic index (`rules/generative/frontend/jwebmp/fullcalendar-pro/README.md`); each linked `.rules.md` file keeps the PACT ↔ RULES ↔ GUIDES ↔ IMPLEMENTATION loop intact and references the architecture artifacts in `docs/architecture/`.
- Follow the linked enterprise rules (JWebMP, Angular 20, TypeScript, logging, testing, GitHub Actions, etc.) from the topic index before touching code so the change remains forward-only and modular.
- Use `docs/PROMPT_REFERENCE.md` and the architecture diagram sources when mapping new behaviors back to the rules so future prompts can load the same context before editing.

## Prompt Language Alignment & Glossary
- The FullCalendar Pro topic glossary (`rules/generative/frontend/jwebmp/fullcalendar-pro/GLOSSARY.md`) is authoritative for this scope; downstream glossaries should link back to it or copy only enforced prompt alignment mappings listed there.
- Host authors should treat `GLOSSARY.md` as the index of enforced mappings and point to the topic glossary for every other term, maintaining the topic-first precedence policy.
- Both glossaries reference the architecture sources so terminology remains traceable through the C4/sequence/ERD artifacts before coding starts.
