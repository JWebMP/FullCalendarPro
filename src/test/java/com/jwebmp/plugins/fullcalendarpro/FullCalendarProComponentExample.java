package com.jwebmp.plugins.fullcalendarpro;

import com.jwebmp.core.base.angular.client.annotations.angular.*;
import com.jwebmp.plugins.fullcalendar.options.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.time.*;

@NgComponent("full-cal-pro-jwebmp")
public class FullCalendarProComponentExample extends FullCalendarPro<FullCalendarProComponentExample>
{
	public FullCalendarProComponentExample()
	{
		super("fcjweb");
		setSelectEvent(new FullCalendarSelectEventTest());
	}
	
	@Override
	public FullCalendarResourceItemsList getInitialResources()
	{
		return new FullCalendarResourceItemsList();
	}
	
	@Override
	public FullCalendarEventsList getInitialEvents()
	{
		FullCalendarEventsList fullCalendarEventsList = new FullCalendarEventsList();
		fullCalendarEventsList.getEvents()
		                      .add(new FullCalendarEvent().setId("1")
		                                                  .setTitle("Event 1")
		                                                  .setStart(LocalDateTime.now()));
		return fullCalendarEventsList;
	}
}
