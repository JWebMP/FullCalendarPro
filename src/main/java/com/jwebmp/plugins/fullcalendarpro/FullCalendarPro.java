package com.jwebmp.plugins.fullcalendarpro;

import com.guicedee.guicedinjection.*;
import com.guicedee.guicedservlets.websockets.*;
import com.jwebmp.core.base.ajax.*;
import com.jwebmp.core.base.angular.implementations.*;
import com.jwebmp.core.base.angular.services.annotations.functions.*;
import com.jwebmp.plugins.fullcalendar.*;
import com.jwebmp.plugins.fullcalendar.options.resources.*;

import java.util.*;

@NgOnDestroy(onDestroy = {
		"this.subscriptionResources?.unsubscribe();",
})
public abstract class FullCalendarPro<J extends FullCalendarPro<J>> extends FullCalendar<J>
{
	
	protected FullCalendarPro()
	{
		super();
	}
	
	
	public FullCalendarPro(String id)
	{
		super(id);
	}
	
	public abstract FullCalendarResourceItemsList getInitialResources();
	
	@Override
	public List<String> componentConstructorBody()
	{
		List<String> out =  super.componentConstructorBody();
		out.add("this.subscriptionResources = this.socketClientService.registerListener(this.listenerName + 'Resources')" +
		           //    ".pipe(bufferTime(100))" +
		           ".subscribe((message : FullCalendarResourceItemsList) => {\n" +
		           "if(this.calendarApi)\n" +
		           "            for(const rs of this.calendarApi.getResources())\n" +
		           "            {\n" +
		           "                this.calendarApi.getResourceById(rs.id)?.remove();\n" +
		           "            }\n" +
		           "            if(message.resources)\n" +
		           "            for (const resource of message.resources) {\n" +
		           "                this.calendarApi?.addResource(resource);\n" +
		           "            }" +
		           // "this.resources = message; " +
		           //"alert('received resources'); \n" +
		           "});\n");
		return out;
	}
	
	@Override
	public List<String> componentMethods()
	{
		List<String> methods = new ArrayList<>();
		methods.add("fetchData(){\n" +
		            "   this.socketClientService.send(this.listenerName + 'Resources',{className :  '" +
		            getClass().getCanonicalName() + "',\n" +
		            "            listenerName: this.listenerName + 'Resources'},this.listenerName + 'Resources');\n" +
		
		            "   this.socketClientService.send(this.listenerName,{className :  '" +
		            getClass().getCanonicalName() + "',\n" +
		            "            listenerName: this.listenerName},this.listenerName);\n" +
		            
		            "}\n");
		return methods;
	}
	
	@Override
	public List<String> componentFields()
	{
		List<String> fields = super.componentFields();
		fields.add(" private subscriptionResources? : Subscription;\n");
		return fields;
	}
	
	protected void registerWebSocketListeners()
	{
		if (!GuicedWebSocket.isWebSocketReceiverRegistered(getListenerName()))
		{
			GuicedWebSocket.addWebSocketMessageReceiver(new InitialEventsReceiver(getListenerName(), getClass()));
			GuicedWebSocket.addWebSocketMessageReceiver(new InitialResourceEventsReceiver(getListenerNameResources(), getClass()));
		}
	}
	
	private static class InitialResourceEventsReceiver extends WebSocketAbstractCallReceiver
	{
		private String listenerName;
		private Class<? extends FullCalendarPro> actionClass;
		
		public InitialResourceEventsReceiver()
		{
		}
		
		public InitialResourceEventsReceiver(String listenerName, Class<? extends FullCalendarPro> actionClass)
		{
			this.listenerName = listenerName;
			this.actionClass = actionClass;
		}
		
		@Override
		public String getMessageDirector()
		{
			return listenerName;
		}
		
		@Override
		public AjaxResponse<?> action(AjaxCall<?> call, AjaxResponse<?> response)
		{
			try
			{
				actionClass = (Class<? extends FullCalendarPro>) Class.forName(call.getClassName());
				listenerName = call.getUnknownFields()
				                   .get("listenerName")
				                   .toString();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			FullCalendarResourceItemsList initialEvents = GuiceContext.get(actionClass)
			                                                          .getInitialResources();
			response.addDataResponse(listenerName, initialEvents);
			return response;
		}
	}
}
