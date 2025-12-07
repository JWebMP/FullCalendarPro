# RULES

## 1. Scope
- This repository packages the **FullCalendar Pro** plugin (artifact `com.jwebmp.plugins:full-calendar-pro`) — a GuicedEE-aware Java 25 library that extends JWebMP’s FullCalendar widget and reuses the TypeScript client models.
- All rules link back to the Rules Repository (`rules/` submodule) while the host docs live at the root (`PACT.md`, `GLOSSARY.md`, `GUIDES.md`, `IMPLEMENTATION.md`).

## 2. Technical Foundations
### Java & Build Tooling
- **Java 25 LTS only**: Follow `rules/generative/language/java/java-25.rules.md` and `rules/generative/language/java/build-tooling.md` for compiler flags, modules, and dependency governance.
- **Maven**: Keep dependency declarations coordinate-only; use `mvn -B verify` for CI runs and avoid mixing Gradle/other DSLs.
- **JPMS-aware modules**: Supply `module-info.java` as shown in source; all service providers must be declared both in the module and `META-INF/services` per GuicedEE expectations.

### Fluency & APIs
- **CRTP-fluent APIs**: Because JWebMP/GuicedEE and CRTP are selected, do not use Lombok `@Builder`; manual setters must cast to the generic self-type with `@SuppressWarnings("unchecked")` (see `rules/generative/backend/fluent-api/README.md`).
- **Logging**: Default to Log4j2 using Lombok `@Log4j2` (ref `rules/generative/backend/logging/LOGGING_RULES.md`). Avoid polluting the log surface with alternative annotation processors.
- **JSpecify nullness**: Adopt package-level `@NullMarked` and annotate exceptions with `@Nullable` following `rules/generative/backend/jspecify/README.md`.

### Frontend & Plugin Topics
- **JWebMP Core/Client/TypeScript**: Follow `rules/generative/frontend/jwebmp/README.md` plus the `client/README.md` (for DI + lifecycle) and `typescript/README.md` (for generated metadata).
- **FullCalendar**: The plugin extends `rules/generative/frontend/jwebmp/fullcalendar/README.md` and its testing patterns.
- **FullCalendar Pro**: Leverage `rules/generative/frontend/jwebmp/fullcalendar-pro/README.md` for Pro-only modules (resource templates, timeline plugins, WebSocket resource fetching) while staying aligned with the base FullCalendar wrapper rules.
- **Angular 20**: When Angular content appears, obey the base Angular rules (`rules/generative/language/angular/README.md`) and the Angular 20 override (`rules/generative/language/angular/angular-20.rules.md`). Do not mix Angular versions.

### Documentation & Architecture
- **Documentation-as-code**: Keep diagrams textual (Mermaid/PlantUML) under `docs/architecture/`; see `rules/generative/architecture/README.md` and `docs/architecture/README.md` for index links.
- **Stage gating & forward-only**: Honor `rules/PROMPT_ADOPT_EXISTING_PROJECT.md`, `rules/PROMPT_NEW_PROJECT.md`, and stage instructions embedded in `rules/GLOSSARY.md`. No stage may proceed without acceptance or recorded optional approvals. All changes erase legacy artifacts unless explicitly retained.
- **Prompt Reference Tracker**: `docs/PROMPT_REFERENCE.md` records selected stacks and links to diagrams; load it in future prompts before generating output.

### Testing & Quality
- **Jacoco**: Use `rules/generative/platform/testing/jacoco.rules.md` to keep coverage focused on the plugin classes.
- **Java Micro Harness**: Align tests with `rules/generative/platform/testing/java-micro-harness.rules.md` for harness setup.
- **BrowserStack**: Document browser validation flows per `rules/generative/platform/testing/browserstack.rules.md` when UI assets (FullCalendar scripts) are exercised.
- **TDD**: Design tests before code per `rules/generative/architecture/tdd/README.md` to keep the turnaround spec-driven.

## 3. CI/CD & Environment
- **GitHub Actions**: The host workflow references `rules/generative/platform/ci-cd/providers/github-actions.md`; keep secrets and environment injection minimal for this library.
- **Secrets & Config**: `.env.example` mirrors `rules/generative/platform/secrets-config/env-variables.md`, ensuring CI environments and any runtime checkers can refer to the same keys.
- **Shared GuicedEE workflow**: Treat the `GuicedEE/Workflows/.github/workflows/projects.yml@master` job (see `rules/generative/backend/guicedee/README.md`) as inspiration; do not import it unless explicitly approved.

## 4. Guides & Implementation Links
- **Guides** describe how to apply these rules (`GUIDES.md`). Each guide entry references the relevant `rules/` file and the architecture diagrams under `docs/architecture/`.
- **Implementation** (`IMPLEMENTATION.md`) mirrors the actual layout (`src/main/resources/META-INF/services/`, `module-info.java`, etc.) and links back to GUIDES/RULES/GLOSSARY.

## 5. Documentation Modularization
- Replace or split monolithic docs (e.g., README) with focused files that reference the rules and architecture diagrams. Keep root-level docs (PACT, RULES, etc.) concise indexes.
- Cross-link `docs/architecture/README.md`, `docs/PROMPT_REFERENCE.md`, and the new `GUIDES.md` so the loop is traceable from high-level architecture through implementation.
