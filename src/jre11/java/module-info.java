import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.fullcalendarpro.*;

module com.jwebmp.plugins.fullcalendarpro {
	requires transitive com.jwebmp.plugins.fullcalendar;
	requires com.guicedee.guicedservlets.websockets;
	
	provides IPageConfigurator with FullCalendarProPageConfigurator;

	opens com.jwebmp.plugins.fullcalendarpro to com.google.guice;
	
}
