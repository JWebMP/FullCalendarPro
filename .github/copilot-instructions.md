# Copilot Instructions
- Load the root `RULES.md` plus `GLOSSARY.md`, `GUIDES.md`, `IMPLEMENTATION.md`, and `docs/architecture/README.md` before making suggestions.
- Honor Stage-Gated Workflow: document architecture (C4/sequence/ERD) before code, track the loop `PACT↔RULES↔GUIDES↔IMPLEMENTATION`, and obey the forward-only and document modularity policies (see `RULES.md` §§4-5).
- Refer to `.env.example` and `rules/generative/platform/secrets-config/env-variables.md` when suggesting environment changes or secrets.
- The repository uses Java 25, Maven, CRTP, JWebMP, GuicedEE client, Log4j2, Jacoco, Java Micro Harness, BrowserStack, and GitHub Actions; suggestions must align with those rules.
- Consult `rules/generative/frontend/jwebmp/fullcalendar-pro/README.md` for any change touching resource/timeline plugins, template hooks, or WebSocket resource loading so Copilot keeps the doc loop and prompt language alignment intact.
