# C4 Component — FullCalendar Pro Plugin

This module is the single container responsible for integrating the Pro features into JWebMP applications. Inside the packaged jar, the key components are:

1. **FullCalendarProModuleScanInclusion** (`src/main/resources/META-INF/services/...IGuiceScanModuleInclusions`) instructs the GuicedEE bootstrapper which new modules to include when scanning the application classpath.
2. **FullCalendarProPageConfigurator** (`META-INF/services/com.jwebmp.core.services.IPageConfigurator`) wires the FullCalendar Pro views into the page lifecycle, ensuring the widget is available to page definitions.
3. **TypeScript Client Artifacts** (pulled from `com.jwebmp.plugins:typescript-client`) supply the shared client models used by the widget and custom elements.
4. **Static Resources** (under `src/main/webapp`/`resources`) provide any JSP/HTML fragments, styles, or script assets added by the plugin.

```mermaid
graph LR
  ModuleScan(FullCalendarProModuleScanInclusion)
  PageConfig(FullCalendarProPageConfigurator)
  TypescriptClient(TypeScript Client Artifacts)
  StaticAssets(Static Web Resources)
  FullcalendarPro(FullCalendar Pro Plugin Container)
  Guicedee(GuicedEE Runtime)

  ModuleScan-->|registers modules with|Guicedee
  PageConfig-->|links pages to|Guicedee
  StaticAssets-->|bundles into|FullcalendarPro
  TypescriptClient-->|bundles with|FullcalendarPro
  FullcalendarPro-->|exposes services via|ModuleScan
  FullcalendarPro-->|provides configurator|PageConfig
