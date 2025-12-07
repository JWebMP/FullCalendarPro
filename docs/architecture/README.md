# Architecture Index

This directory holds the architecture documentation for the FullCalendar Pro plugin adoption of the Rules Repository. Each document is written in Mermaid to keep everything diff-friendly.

| Document | Purpose |
| --- | --- |
| [C4 Context](./c4-context.md) | Establishes the system context and high-level dependencies for the plugin within the JWebMP ecosystem.
| [C4 Container](./c4-container.md) | Describes how the Maven build, packaged jar, runtime, and browser collaborate.
| [C4 Component](./c4-component-fullcalendar-pro.md) | Breaks down the key service provider components in the Pro jar, including actual `META-INF/services` entries.
| [Sequence: Plugin Packaging](./sequence-package-fullcalendar.md) | Shows how developers/CI package the artifact with Maven.
| [Sequence: Runtime Integration](./sequence-runtime-wiring.md) | Details how the JWebMP runtime discovers and integrates the pro components.
| [ERD: Core Domain](./erd-core-domain.md) | Captures the lightweight domain model for widgets, resources, configuration, and the TypeScript bridge.

Each Mermaid source is the authoritative artifact; generated images (if any) should mirror these files.
