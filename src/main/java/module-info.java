import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.fullcalendarpro.FullCalendarProPageConfigurator;

module com.jwebmp.plugins.fullcalendarpro {
    exports com.jwebmp.plugins.fullcalendarpro;
    requires transitive com.jwebmp.plugins.fullcalendar;
    requires guiced.vertx.sockets;
    requires com.jwebmp.client;
    requires com.jwebmp.core.angular;

    provides IPageConfigurator with FullCalendarProPageConfigurator;

    opens com.jwebmp.plugins.fullcalendarpro to com.google.guice;

}
