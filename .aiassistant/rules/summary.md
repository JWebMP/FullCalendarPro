# AI Assistant Rules Summary
- **RULES §4 (Guides & Implementation Links)**: Always cross-reference `GUIDES.md`, `IMPLEMENTATION.md`, and `docs/architecture/README.md` when discussing rollout; keep loops between guides/rules/implementation closed.
- **RULES §5 (Documentation Modularization)**: Produce focused docs, avoid monoliths, and update all references in the same forward-only change set.
- **Document Modularity Policy**: Prefer small, linkable files; architecture diagrams and prompts must reside under `docs/architecture/` and `docs/PROMPT_REFERENCE.md`.
- **Forward-Only Change Policy**: Remove or replace outdated docs/terms; do not maintain legacy anchors unless explicitly requested.
- **FullCalendar Pro Rules Update**: The host adds `rules/generative/frontend/jwebmp/fullcalendar-pro`; consult that topic index for Pro-specific wiring and always link it to the architecture/diagram sources before editing related code.
