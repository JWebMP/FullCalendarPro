# GUIDES

These guides explain how to apply the Rules Repository conventions while working inside the FullCalendar Pro project. Each section closes the loop by referencing the relevant rules, glossary entries, architecture artifacts, and implementation notes.

## 1. Packaging & Module Configuration
- Follow `rules/generative/language/java/build-tooling.md` for Maven lifecycle, artifact coordinates, and module-info management. Keep the root `pom.xml` aligned with parent BOM coordinates (`com.jwebmp:parent:2.0.0-SNAPSHOT`).
- Document the artifact’s responsibilities in `IMPLEMENTATION.md`, noting the service descriptors under `src/main/resources/META-INF/services` (`FullCalendarProModuleScanInclusion`, `FullCalendarProPageConfigurator`).
- Reference `docs/architecture/sequence-package-fullcalendar.md` when describing the packaging flow in design discussions.

## 2. Runtime & GuicedEE Wiring
- Use `rules/generative/backend/guicedee/client/README.md` to register SPI providers, honor JPMS module resolutions, and keep CRTP-friendly configuration builders.
- Connect the `FullCalendarProPageConfigurator` lifecycle to the `FullCalendarProModuleScanInclusion` module by following the component breakdown in `docs/architecture/c4-component-fullcalendar-pro.md`.
- Keep integration notes inside `IMPLEMENTATION.md` to explain how the jar is discovered by the JWebMP runtime (see `docs/architecture/sequence-runtime-wiring.md`).
- **Resource/Timeline listener registration**: `FullCalendarPro.registerWebSocketListeners()` (see `src/main/java/com/jwebmp/plugins/fullcalendarpro/FullCalendarPro.java:86-154`) adds a `InitialResourceEventsReceiver` to GuicedEE whenever the `listenerName + 'Resources'` channel lacks a receiver. That WebSocket call receiver (`InitialResourceEventsReceiver` lines 152-194) captures the listener name from the incoming `AjaxCall`, resolves the originating `FullCalendarPro` subclass via `IGuiceContext`, invokes `getInitialResources()`, and returns the list via `response.addDataResponse(listenerName, initialEvents)`. The Angular component also emits `'Resources'` (via `fetchData()` in `methods()`, lines 111-138) and listens for the response through the event bus subscription defined by `initializeResources()` and `handleResourceEvents()` (`NgMethod` blocks near the top of the file). Together these paths ensure resource groups/timelines (`resourceDayGrid`, `resourceTimeGrid`, `resourceTimeline` plugins) receive initial data before the calendar renders.

## 3. Logging, Nullness, & Fluent APIs
- Default to Log4j2 with `@Log4j2` per `rules/generative/backend/logging/LOGGING_RULES.md`. Document logger naming conventions and level control in `IMPLEMENTATION.md` if custom appenders or formatters are needed.
- Apply the JSpecify guidance from `rules/generative/backend/jspecify/README.md`; annotate modulewide defaults with `@NullMarked` and only permit `@Nullable` where contracts demand it.
- Implement CRTP manual setters as described in `rules/generative/backend/fluent-api/crtp.rules.md` whenever you extend core configuration classes.

## 4. Testing & Quality
- Keep tests aligned with TDD per `rules/generative/architecture/tdd/README.md`; scripts should fail fast if `module-info.java` is misconfigured.
- Instrument coverage with Jacoco following `rules/generative/platform/testing/jacoco.rules.md`; review coverage reports in GitHub Actions artifacts.
- Relay any cross-browser test requirements to BrowserStack (see `rules/generative/platform/testing/browserstack.rules.md`) when verifying the generated FullCalendar scripts and client assets.
- Use the Java Micro Harness patterns from `rules/generative/platform/testing/java-micro-harness.rules.md` for unit/integration harness scaffolding.

## 5. CI/CD & Secrets
- The GitHub Actions workflow in `.github/workflows/maven-build.yml` (see `rules/generative/platform/ci-cd/providers/github-actions.md`) performs `mvn -B verify` on pushes/PRs and publishes coverage summaries.
- Document required secrets (if any) inside `.github/workflows/maven-build.yml` and top-level `README.md`; keep the overarching policy in `rules/generative/platform/secrets-config/env-variables.md` and `.env.example`.

## 6. Documentation & Architecture
- Always refer back to `docs/architecture/README.md` when describing the system context or container responsibilities; architecture diagrams remain the single source for C4/ERD/sequence flows.
- Note `docs/PROMPT_REFERENCE.md` when updating prompts or instructions so future AI agents have stack context.
- Ensure `GUIDES.md`, `RULES.md`, `GLOSSARY.md`, and `IMPLEMENTATION.md` cross-link each other, especially when referencing terms listed under those files.

## 7. FullCalendar Pro Behavior
- Before introducing resource timelines or template slots, follow `rules/generative/frontend/jwebmp/fullcalendar-pro/README.md` and its component rules (`options-and-layout`, `events-and-resources`, `angular-integration`, `testing`, etc.) to keep the Pro wiring consistent.
- The Pro rules walk through `FullCalendarPro`’s resource fetch listeners, WebSocket receivers, and Angular template hooks (`FullCalendarPro.class`, `InitialResourceEventsReceiver`, `NgTemplateElement` helpers) so implementations remain tied to the documented flows under `docs/architecture/c4-component-fullcalendar-pro.md` and `docs/architecture/sequence-runtime-wiring.md`.
- Tie any release updates to `rules/generative/frontend/jwebmp/fullcalendar-pro/release-notes.md` so host consumers see the same forward-only change summary as the rules index.
