# Prompt Reference Tracker

This file captures the adoption metadata that downstream prompts must load before making changes.

## Selected Language & Tooling
- **Java LTS**: Java 25 (per host prompt). See `rules/generative/language/java/java-25.rules.md` and `rules/generative/language/java/build-tooling.md` for tooling constraints.
- **Build Tool**: Maven (standard for the host module and mandated by rules). Dependency coordinates are declared in `pom.xml` and should obey the Maven rules guide.
- **Frontend/Plugin Stacks**: The extension wraps [JWebMP](rules/generative/frontend/jwebmp/README.md) along with the `FullCalendar`/`FullCalendar Pro` widgets (`rules/generative/frontend/jwebmp/fullcalendar/README.md` if available) and leans on TypeScript client models (`rules/generative/frontend/jwebmp/typescript/README.md`). Consult `rules/generative/frontend/jwebmp/fullcalendar-pro/README.md` for Pro-only patterns (resource templates, WebSocket resource loading) before editing the host plugin.
- **Testing & Logging**: Jacoco, Java Micro Harness, BrowserStack, Log4j2 (per RULES selection).
- **CI/CD**: GitHub Actions is the chosen provider (`rules/generative/platform/ci-cd/providers/github-actions.md`).
- **Architecture & Documentation**: Documentation-first approach with C4 diagrams, sequences, ERD, and glossary cross-links (see `docs/architecture/README.md`).

## Diagram Sources
- **Context**: `docs/architecture/c4-context.md`
- **Container**: `docs/architecture/c4-container.md`
- **Component (FullCalendar Pro)**: `docs/architecture/c4-component-fullcalendar-pro.md`
- **Sequence - Packaging**: `docs/architecture/sequence-package-fullcalendar.md`
- **Sequence - Runtime**: `docs/architecture/sequence-runtime-wiring.md`
- **ERD - Core Domain**: `docs/architecture/erd-core-domain.md`

## Glossary & Stage Loop
- Glossary is topic-first and will reference `rules/GLOSSARY.md` anchors (e.g., `rules/generative/frontend/jwebmp/typescript/GLOSSARY.md` when available).
- The PACT, RULES, GUIDES, IMPLEMENTATION, and GLOSSARY docs must cross-link each other and the architecture/diagram sources to enforce the loop (per prompt instructions).
