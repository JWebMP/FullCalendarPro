package com.jwebmp.plugins.fullcalendarpro;

import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.base.html.*;
import com.jwebmp.plugins.fullcalendar.events.*;
import org.junit.jupiter.api.*;

public class FullCalendarSelectEventTest extends FullCalendarSelectEvent
{
	
	@Test
	public void testOnCreate()
	{
		DivSimple<?> d = new DivSimple<>();
		FullCalendarSelectEvent e = new FullCalendarSelectEvent(){
			
			@Override
			public void onSelect(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarSelectEventInfo selectEvent)
			{
				System.out.println("Selected");
			}
		};
		d.addEvent(e);
		
		System.out.println(d.toString(0));
		
		System.out.println(e.renderJavascript());
		System.out.println(d.renderJavascript());
	}
	
	@Override
	public void onSelect(AjaxCall<?> call, AjaxResponse<?> response, FullCalendarSelectEventInfo selectEvent)
	{
		System.out.println("selected");
	}
}