# Implementation

This document captures the observable implementation layout for the FullCalendar Pro plugin and links it back to the guides, rules, glossary, and architecture artifacts.

## 1. Maven Module
- `pom.xml` defines the jar packaging (`com.jwebmp.plugins:full-calendar-pro`) and inherits from the `com.jwebmp:parent:2.0.0-SNAPSHOT` BOM. Dependencies are limited to `jwebmp-core`, the base `full-calendar`, the `typescript-client`, and the JWebMP testing support.
- Build tooling and artifact rules refer to `rules/generative/language/java/build-tooling.md`, with CI tied to `rules/generative/platform/ci-cd/providers/github-actions.md` and the workflow under `.github/workflows/maven-build.yml`.

## 2. Source Layout
- `src/main/java/module-info.java` declares the JPMS module and exports/opens packages required by GuicedEE. Keep this file synchronized with the `META-INF/services` entries (see next section).
- `src/main/resources/META-INF/services/` contains two service descriptors:
  - `com.guicedee.client.services.config.IGuiceScanModuleInclusions` → `com.jwebmp.plugins.fullcalendarpro.implementations.FullCalendarProModuleScanInclusion`
  - `com.jwebmp.core.services.IPageConfigurator` → `com.jwebmp.plugins.fullcalendarpro.FullCalendarProPageConfigurator`
  These service providers drive the module scan and page configuration flows described in `docs/architecture/c4-component-fullcalendar-pro.md` and `docs/architecture/sequence-runtime-wiring.md`.

## 3. Resources & Static Assets
- `src/main/resources` also hosts the service config files and can include additional CSS/JS fragments if the plugin ever ships custom assets. Document any new assets in `IMPLEMENTATION.md`, referencing the architecture docs to show how they fit into the container responsibilities.
- `src/main/webapp/WEB-INF` is currently empty, but the folder indicates potential server-side view fragments; updates should respect the modular doc-first policy (describe changes in `GUIDES.md` before adding markup).

## 4. Tests
- The placeholder `.gitignore` files under `src/test/java` & `src/test/resources` show the intention to keep test sources lean. Any new test classes must follow the TDD workflow (see `rules/generative/architecture/tdd/README.md`), use the Java Micro Harness scaffolding (per `rules/generative/platform/testing/java-micro-harness.rules.md`), and be instrumented with Jacoco (`rules/generative/platform/testing/jacoco.rules.md`).

## 5. Documentation Loop
- Architecture diagrams appear in `docs/architecture/`; reference them whenever a new component or flow is introduced.
- The glossary index (`GLOSSARY.md`), rules (`RULES.md`), guides (`GUIDES.md`), and this implementation note cross-reference each other to keep terminology and expectations aligned (see `docs/PROMPT_REFERENCE.md`).
- The implementation notes tie back to the FullCalendar Pro topic index (`rules/generative/frontend/jwebmp/fullcalendar-pro/README.md`) so every service descriptor, template hook, and resource push can be mapped to the forward-only ruleset.
