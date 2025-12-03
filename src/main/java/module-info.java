import com.guicedee.client.services.config.IGuiceScanModuleInclusions;
import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.fullcalendarpro.FullCalendarProPageConfigurator;
import com.jwebmp.plugins.fullcalendarpro.implementations.FullCalendarProModuleScanInclusion;

module com.jwebmp.plugins.fullcalendarpro {
    exports com.jwebmp.plugins.fullcalendarpro;
    requires transitive com.jwebmp.plugins.fullcalendar;

    requires com.jwebmp.client;
    requires com.jwebmp.core;
    requires com.jwebmp.core.angular;
    requires com.guicedee.guicedinjection;

    provides IPageConfigurator with FullCalendarProPageConfigurator;
    provides IGuiceScanModuleInclusions with FullCalendarProModuleScanInclusion;

    opens com.jwebmp.plugins.fullcalendarpro to com.google.guice;

}
