# C4 Container — FullCalendar Pro Delivery

This section describes how the FullCalendar Pro plugin is packaged and interacts with surrounding systems.

- **Maven Build System** compiles Java sources, assembles `META-INF/services` metadata, and packages the full artifact.
- **FullCalendar Pro JAR** contains the service providers (`FullCalendarProPageConfigurator` and `FullCalendarProModuleScanInclusion`), static resources, and TypeScript bindings that hook into JWebMP.
- **JWebMP Runtime (Consumer)** is the embeddable Java servlet/runtime that loads the artifact, scans for service providers, and exposes Pro views to end users.
- **Web Browser / Client** renders the compiled JWebMP page, which references the FullCalendar widget assets shipped with this module.

```mermaid
graph TD
  Maven(Maven Build)
  FullcalendarProJar(FullCalendar Pro JAR)
  GuicedeeModule(GuicedEE Scan Modules)
  PageConfigurator(FullCalendarProPageConfigurator)
  JwebmpRuntime(JWebMP Runtime)
  Browser(Web Browser)

  Maven-->|packages|FullcalendarProJar
  FullcalendarProJar-->|provides services|GuicedeeModule
  FullcalendarProJar-->|registers pages|PageConfigurator
  JwebmpRuntime-->|loads|FullcalendarProJar
  JwebmpRuntime-->|exposes pages to|Browser
  Browser-->|renders FullCalendar|JwebmpRuntime
