# Roo Workspace Policy
- **Rule Anchors**: Load `RULES.md` plus `GLOSSARY.md`, `GUIDES.md`, `IMPLEMENTATION.md`, `docs/architecture/README.md`, and the stage-gated instructions in `PACT.md` before making changes.
- **Behavioral Sections (RULES §4)**: Always cross-link guides, implementation notes, and architecture diagrams so the document loop stays intact. Reference `docs/architecture/README.md` whenever describing flows or containers.
- **Technical Sections (RULES §5)**: Modularize each document; avoid monolithic files and update all references (forward-only) when reorganizing assets.
- **Document Modularity Policy**: Keep diagrams textual, store them under `docs/architecture/`, and describe them in adjacent Markdown with Mermaid/PlantUML sources.
- **Forward-Only Change Policy**: Remove outdated artifacts, never rely on legacy copies, and avoid reintroducing replaced docs in future stages unless explicitly requested.
