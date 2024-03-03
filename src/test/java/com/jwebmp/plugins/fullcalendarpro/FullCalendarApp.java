package com.jwebmp.plugins.fullcalendarpro;

import com.jwebmp.core.base.angular.client.annotations.angular.*;
import com.jwebmp.core.base.angular.services.*;

@NgApp(value = "fullcalendarpro", bootComponent = FullCalendarExample.class)
public class FullCalendarApp extends NGApplication<FullCalendarApp>
{
    public FullCalendarApp()
    {
        getOptions().setTitle("Full Calendar Pro App");
    }

}
