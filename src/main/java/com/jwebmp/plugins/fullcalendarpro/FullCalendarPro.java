package com.jwebmp.plugins.fullcalendarpro;

import com.guicedee.client.IGuiceContext;
import com.guicedee.guicedservlets.websockets.options.IGuicedWebSocket;
import com.jwebmp.core.base.ajax.AjaxCall;
import com.jwebmp.core.base.ajax.AjaxResponse;
import com.jwebmp.core.base.angular.client.annotations.constructors.NgConstructorBody;
import com.jwebmp.core.base.angular.client.annotations.functions.NgAfterViewInit;
import com.jwebmp.core.base.angular.client.annotations.functions.NgOnDestroy;
import com.jwebmp.core.base.angular.client.annotations.references.NgImportReference;
import com.jwebmp.core.base.angular.client.annotations.structures.NgField;
import com.jwebmp.core.base.angular.client.annotations.structures.NgMethod;
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

@NgField("private handlerResourcesId : string;")
@NgConstructorBody("this.handlerResourcesId = this.generateHandlerId();")

@NgMethod("""
        \tinitializeResources() {
                  // General listener
                  const resourcesObserver = {
                      next: (data: any) => this.handleResourceEvents(data),
                      error: (err: any) =>
                          console.error(`Error in resources listener:`, err),
                      complete: () =>
                          console.log('Resources listener completed'),
                  };
                  this.subscriptionResources = this.eventBusService
                      .listen(this.listenerName + 'Resources', this.handlerResourcesId)
                      .subscribe(resourcesObserver);
              }
        """)

@NgMethod("""
        \thandleResourceEvents(data: any) {
                  if (typeof data === 'string') {
                      const d = JSON.parse(data);
                      if (Array.isArray(d)) {
                          this.calendarOptions.resources = d;
                      }
                  } else {
                      this.calendarOptions.resources = data;
                  }
              }
        """)

@NgAfterViewInit("""
        \tthis.initializeResources();
        """)
@NgOnDestroy("this.subscriptionResources?.unsubscribe();")
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
    public List<String> methods()
    {
        //List<String> methods = super.methods();
        List<String> methods = new ArrayList<>();
        methods.add("fetchData() {\n" +
                "this.eventBusService.send(this.listenerName + 'Options', {\n" +
                "            className: this.clazzName,\n" +
                "            listenerName: this.listenerName + 'Options'\n" +
                "        }, this.listenerName + 'Options');" +
                "" +
                "" +
                "        this.eventBusService.send(this.listenerName + 'Resources', {\n" +
                "            className: this.clazzName,\n" +
                "            listenerName: this.listenerName + 'Resources'\n" +
                "        }, this.listenerName + 'Resources');\n" +

                "" +
                "" +
                "                 //   alert('fetching evnts');\n" +
                "                    this.eventBusService.send(this.listenerName, {\n" +
                "                        className: this.clazzName,\n" +
                "                        listenerName: this.listenerName\n" +
                "                    }, this.listenerName);\n" +
                "    }"
        );
/*
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
                            "    }");*/

        return methods;
    }

    @Override
    public List<String> fields()
    {
        List<String> fields = super.fields();
        fields.add(" private subscriptionResources? : Subscription;\n");
        return fields;
    }

    protected void registerWebSocketListeners()
    {
        super.registerWebSocketListeners();
        if (!IGuicedWebSocket.isWebSocketReceiverRegistered(getListenerNameResources()))
        {
            IGuicedWebSocket.addWebSocketMessageReceiver(new InitialResourceEventsReceiver(getListenerNameResources(), getClass()));
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
            } catch (ClassNotFoundException e)
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
