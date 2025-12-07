# C4 Context — FullCalendar Pro Plugin

This repository delivers the **FullCalendar Pro** extension library that plugs into the broader JWebMP ecosystem. The library is consumed by Java applications and front-end pages that rely on the JWebMP runtime, the FullCalendar widget, and the generated TypeScript client, all tied together through Maven-based build tooling.

Key contexts observed from the repository:
- **JWebMP Core runtime** (project dependency `com.jwebmp.core:jwebmp-core`) hosts page lifecycles and service discovery via `IPageConfigurator`/GuicedEE modules.
- **FullCalendar** base plugin (`com.jwebmp.plugins:full-calendar`) provides the base widgets that Pro builds on.
- **TypeScript client** (`com.jwebmp.plugins:typescript-client`) brings shared client-side models that this module reuses.
- **Developers** (build/test) interact with the Maven project to compile/package the Pro plugin jar and publish it to consumers.

```mermaid
graph LR
  Developer(Developer / CI)
  Browser(Web Browser)
  JwebmpCore(JWebMP Core Runtime)
  Fullcalendar(FullCalendar Base Plugin)
  TypescriptClient(TypeScript Client Artifacts)
  FullcalendarPro(FullCalendar Pro Plugin)

  Developer-->|builds|FullcalendarPro
  FullcalendarPro-->|depends on|JwebmpCore
  FullcalendarPro-->|extends|Fullcalendar
  FullcalendarPro-->|reuses|TypescriptClient
  Browser-->|loads pages from|JwebmpCore
  JwebmpCore-->|integrates plugin via services|FullcalendarPro
