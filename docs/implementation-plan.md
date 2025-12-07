# Implementation Plan — Stage 3

This document captures the scaffolding and rollout plan before any new code commits. It refers to the existing module layout and the architecture artifacts kept under `docs/architecture/`.

## 1. Scaffolding & Module Tree
- The module tree is flat: `pom.xml`, `module-info.java`, `src/main/java`, `src/main/resources`, and the service descriptors under `src/main/resources/META-INF/services`.
- Whenever new features or packages are added, document the new package/module in `IMPLEMENTATION.md` and update `docs/architecture/c4-component-fullcalendar-pro.md` if the component boundaries move.
- Keep service provider declarations synchronized between `module-info.java` and the `META-INF/services` files. Re-run `mvn -DskipTests clean package` to ensure the module's `module-info` exports/opens remain consistent.

## 2. Build & Annotation Processor Wiring
- Maven remains the orchestrator (`mvn -B -V verify` in CI). Any added annotation processors must appear in `pom.xml` and follow `rules/generative/language/java/build-tooling.md`.
- Maintain CRTP fluent APIs (no Lombok builders) and keep `@Log4j2` logging consistent with `rules/generative/backend/logging/LOGGING_RULES.md`.
- Nullness is enforced via JSpecify annotations referenced in `rules/generative/backend/jspecify/README.md`; update package-info files if new packages are introduced.

## 3. CI, Environment, Secrets Plan
- The GitHub Actions workflow (`.github/workflows/maven-build.yml`) verifies the build; any additional steps (publishing, coverage, BrowserStack tests) should be appended to future job sections and documented in `GUIDES.md`.
- Secrets follow `.env.example` and `rules/generative/platform/secrets-config/env-variables.md`. Document any new keys introduced by future code under `.env.example` and ensure GitHub Actions uses them via repository secrets.

## 4. Rollout & Validation
1. **Doc review**: Before coding, confirm `RULES.md`, `GUIDES.md`, `GLOSSARY.md`, and architecture diagrams capture the planned change.
2. **Local build**: Run `mvn -B -V verify` to ensure module boundaries and services compile with JDK 25.
3. **CI validation**: Push to a branch/PR and let GitHub Actions run the workflow; inspect Jacoco reports and BrowserStack triggers if applicable.
4. **Release readiness**: After validation, update `IMPLEMENTATION.md` with any new assets and ensure `README.md`/`docs/PROMPT_REFERENCE.md` link to new diagrams or stacks.

## 5. Risk Items & Mitigations
- **Missing UI assets** — Because the repo currently lacks JS/CSS files, adding them risks breaking the packaging flow. Mitigate by describing the assets in `GUIDES.md` and referencing `docs/architecture/sequence-runtime-wiring.md` before introducing them.
- **Module-info drift** — JPMS module/`META-INF/services` can fall out of sync. Always re-run the Maven build after edits that touch package exports or service registrations.
- **Secret handling regressions** — Document new env variables in `.env.example` and the secrets guide; avoid hardcoding placeholders in the code.
- **Policy gap** — Keep the stage-gated artifacts consistent: if stage approvals are required later, they must reference this plan and affirm the diagrams/guides again.

## 6. Validation Checklist
- [x] Architecture diagrams under `docs/architecture/` describe current flows.
- [x] Docs loop is closed (`PACT ↔ GLOSSARY ↔ RULES ↔ GUIDES ↔ IMPLEMENTATION`).
- [x] `docs/PROMPT_REFERENCE.md` lists the stacks and links to diagrams.
- [x] `.env.example` aligns with `rules/generative/platform/secrets-config/env-variables.md`.
- [x] GitHub workflow runs Maven verify with JDK 25.
