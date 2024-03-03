package com.jwebmp.plugins.fullcalendarpro;

import com.guicedee.client.IGuiceContext;
import com.guicedee.guicedservlets.websockets.GuicedWebSocket;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.angular.client.annotations.functions.NgOnDestroy;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportReference;
import com.jwebmp.core.base.angular.implementations.WebSocketAbstractCallReceiver;
import com.jwebmp.plugins.fullcalendar.FullCalendar;
import com.jwebmp.plugins.fullcalendar.options.resources.FullCalendarResourceItemsList;

import java.util.ArrayList;
import java.util.List;

@NgOnDestroy("this.subscriptionResources?.unsubscribe();")


@NgImportReference(value = "!resourceDayGridPlugin", reference = "@fullcalendar/resource-daygrid")
@NgImportReference(value = "!resourceTimeGridPlugin", reference = "@fullcalendar/resource-timegrid")
@NgImportReference(value = "!resourceTimelinePlugin", reference = "@fullcalendar/resource-timeline")
@NgImportReference(value = "!adaptivePlugin", reference = "@fullcalendar/adaptive")

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
        List<String> out = super.componentConstructorBody();
        out.add("this.subscriptionResources = this.socketClientService.registerListener(this.listenerName + 'Resources').subscribe((message: any) => {\n" +
                "          //  alert('bla - --- ' + JSON.stringify(message));\n" +
                "            let workabe = false;\n" +
                "           if(message) " +
                "           if (Array.isArray(message)) {\n" +
                "                workabe = true;\n" +
                "            } else {\n" +
                "                if (message.out && message.out[0]) {\n" +
                "                    message = message.out[0];\n" +
                "                    workabe = true;\n" +
                "                }\n" +
                "            }\n" +
                "\n" +
                "            if(workabe)\n" +
                "            {\n" +
                "                try {\n" +
                //      "                    if (this.calendarApi)\n" +
                //     "                        for (const rs of this.calendarApi.getResources()) {\n" +
                //    "                            this.calendarApi.getResourceById(rs.id)?.remove();\n" +
                //    "                        }\n" +
                "                    for (const resource of message) {\n" +
                "                      //  alert('adding resource -' + JSON.stringify(resource));\n" +
                "                        this.calendarApi?.addResource(resource);\n" +
                "                    }\n" +
                //     "                    this.calendarApi?.updateSize();\n" +
                "                } catch (e) {\n" +
                "                    console.log(\"error in resources\", e);\n" +
                "                }\n" +
                "            }\n" +
                "        });");
        return out;
    }

    @Override
    public List<String> componentMethods()
    {
        List<String> methods = new ArrayList<>();
        methods.add("fetchData() {\n" +
                "this.socketClientService.send(this.listenerName + 'Options', {\n" +
                "            className: '" + getClass().getCanonicalName() + "',\n" +
                "            listenerName: this.listenerName + 'Options'\n" +
                "        }, this.listenerName + 'Options');" +
                "" +
                "" +
                "        this.socketClientService.send(this.listenerName + 'Resources', {\n" +
                "            className: '" + getClass().getCanonicalName() + "',\n" +
                "            listenerName: this.listenerName + 'Resources'\n" +
                "        }, this.listenerName + 'Resources');\n" +

                "" +
                "" +
                "                 //   alert('fetching evnts');\n" +
                "                    this.socketClientService.send(this.listenerName, {\n" +
                "                        className: '" + getClass().getCanonicalName() + "',\n" +
                "                        listenerName: this.listenerName\n" +
                "                    }, this.listenerName);\n" +
                "    }"
        );

        methods.add(" ngAfterContentChecked(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngAfterContentInit(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngAfterViewChecked(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngAfterViewInit(): void {\n" +
                "\t\t   this.calendarApi = this.calendarComponent?.getApi();\n" +
                "       this.fetchData();\n" +
                "}\n" +
                "\n" +
                "    ngOnDestroy(): void {\n" +
                "    }\n" +
                "\n" +
                "    ngOnInit(): void {\n" +
                "    }");

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
        super.registerWebSocketListeners();
        if (!GuicedWebSocket.isWebSocketReceiverRegistered(getListenerNameResources()))
        {
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
            FullCalendarResourceItemsList initialEvents = IGuiceContext.get(actionClass)
                                                                       .getInitialResources();
            if (initialEvents == null)
            {
                return null;
            }
            response.addDataResponse(listenerName, initialEvents);
            return response;
        }
    }
}
