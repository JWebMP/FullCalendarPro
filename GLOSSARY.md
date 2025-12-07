# Glossary

## Glossary Precedence Policy
- **Topic-first**: When a topic glossary exists (e.g., GuicedEE Inject Client, Testing, Logging), it is the canonical source for terms within that scope. The host glossary acts as an index and only copies enforced Prompt Language Alignment mappings; everything else links back to the topic file.
- **Forward-only**: Remove or update terms in lockstep with their references. Do not keep deprecated synonyms.
- **Document modular**: Keep each entry focused and consider creating a new host subtopic (folder/file) if descriptive text grows beyond a paragraph.

## Topics and References
### Architecture & Process
- **Specification-Driven Design (SDD)** — See `rules/generative/architecture/README.md` for how the doc-first process structures Pact/Rules/Guides/Implementation loops.
- **Documentation-as-Code** — Linked artifacts under `rules/generative/architecture/README.md` and the `docs/architecture/` folder keep diagrams and descriptions textual.
- **Stage-Gated Workflow** — The canonical explanation lives in `rules/PROMPT_ADOPT_EXISTING_PROJECT.md` and is referenced from this repo’s `PACT.md`.
- **Forward-Only Change Policy** — Rooted in `rules/GLOSSARY.md` and `rules/generative/architectures/README.md`; embraces drop-and-replace editing with no compatibility leftovers.

### Java & Build
- **Java 25 LTS** — All source, module-info, and Lombok annotations must align with `rules/generative/language/java/java-25.rules.md` and the associated build-tooling guide.
- **Maven Build** — The `pom.xml` relies on patterns from `rules/generative/language/java/build-tooling.md`; new dependencies must use coordinate-only declarations.
- **JPMS & module-info** — GuicedEE integration demands explicit `module-info.java` entries; see `rules/generative/backend/guicedee/README.md` and the JWebMP module examples.

### GuicedEE & Service Wiring
- **GuicedEE Inject Client** — The host plugin registers `FullCalendarProModuleScanInclusion` and `FullCalendarProPageConfigurator` per `rules/generative/backend/guicedee/client/README.md` and its `GLOSSARY.md`.
- **Service Provider Interfaces (SPI)** — GuicedEE SPIs (module inclusion and page configurators) follow the SPI conventions detailed in `rules/generative/backend/guicedee/client/README.md`.

### JWebMP Frontend Stack
- **JWebMP Core / Client** — See `rules/generative/frontend/jwebmp/README.md` plus `client/README.md` for bundler/servlet expectations, page lifecycle, and TypeScript metadata.
- **FullCalendar & FullCalendar Pro** — The widget definitions and plugin scaffolding are governed by `rules/generative/frontend/jwebmp/fullcalendar/README.md`.
  The Pro-specific glossary lives at `rules/generative/frontend/jwebmp/fullcalendar-pro/GLOSSARY.md`, which includes enforced prompt alignment mappings for resource timelines, template slots, and Angular listeners; reference it before copying any definitions into the host glossary.
- **JWebMP TypeScript Client** — Shared client models shipping with the artifact reference `rules/generative/frontend/jwebmp/typescript/README.md`; generated TS is not hand-modified.
- **Angular 20 (Override)** — Any Angular-related generation follows `rules/generative/language/angular/README.md` + `rules/generative/language/angular/angular-20.rules.md` to avoid mixing versions.

### Logging & Nullness
- **Log4j2 (with Lombok @Log4j2)** — Logged events must follow the standards in `rules/generative/backend/logging/LOGGING_RULES.md`; Lombok loggers use `@Log4j2` as the default annotation.
- **JSpecify Nullness** — The non-null-by-default policy is described in `rules/generative/backend/jspecify/README.md`; annotate with `@NullMarked`/`@Nullable` as per the linked rules.

### Testing & Quality
- **Jacoco** — Coverage instrumentation follows `rules/generative/platform/testing/jacoco.rules.md` (instrument only what’s tested, share exec data via CI artifacts).
- **Java Micro Harness** — Tests run through the micro harness patterns captured in `rules/generative/platform/testing/java-micro-harness.rules.md`.
- **BrowserStack** — Cross-browser front-end/JS interactions reference `rules/generative/platform/testing/browserstack.rules.md` and should be triggered from CI when UI artifacts emerge.
- **TDD** — Tests precede implementation; see `rules/generative/architecture/tdd/README.md` for red/green/refactor loops.

### CI/CD & Secrets
- **GitHub Actions** — The workflow template is described in `rules/generative/platform/ci-cd/README.md` with provider-specific guidance in `rules/generative/platform/ci-cd/providers/github-actions.md`.
- **Environment Variables** — `.env.example` mirrors the canonical variables from `rules/generative/platform/secrets-config/env-variables.md` and documents which secrets the GitHub workflow expects.

### Docs & Diagrams
- **Architecture Diagrams** — All C4, sequences, and ERDs are under `docs/architecture/` and referenced from `docs/architecture/README.md` to satisfy the docs-as-code requirement.
- **Prompt Reference Tracker** — `docs/PROMPT_REFERENCE.md` records stack selections and diagram links so future prompts load this context before modifications.

## Enforced Prompt Language Mapping
- The host glossary copies only the canonical references (no direct term duplication) unless a WebAwesome component is selected. In that event, the specified renamed components (WaButton, WaInput, etc.) would be mirrored here.

## Cross-links
- PACT ↔ `PACT.md`
- RULES ↔ `RULES.md`
- GUIDES ↔ `GUIDES.md`
- IMPLEMENTATION ↔ `IMPLEMENTATION.md`
- Architecture ↔ `docs/architecture/README.md`
