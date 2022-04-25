package com.jwebmp.plugins.fullcalendarpro;

import com.jwebmp.core.base.angular.services.*;
import com.jwebmp.core.base.angular.services.annotations.*;

@NgApp(name = "fullcalendarpro", bootComponent = FullCalendarExample.class)
public class FullCalendarApp extends NGApplication<FullCalendarApp>
{
	public FullCalendarApp()
	{
		getOptions().setTitle("Full Calendar Pro App");
	}
	
}
