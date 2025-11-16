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
import com.jwebmp.plugins.fullcalendar.NgTemplateElement;
import com.jwebmp.plugins.fullcalendar.options.resources.FullCalendarResourceItemsList;

import java.util.ArrayList;
import java.util.List;

@NgOnDestroy("this.subscriptionResources?.unsubscribe();")


@NgImportReference(value = "resourceDayGridPlugin", reference = "@fullcalendar/resource-daygrid", wrapValueInBraces = false)
@NgImportReference(value = "resourceTimeGridPlugin", reference = "@fullcalendar/resource-timegrid", wrapValueInBraces = false)
@NgImportReference(value = "resourceTimelinePlugin", reference = "@fullcalendar/resource-timeline", wrapValueInBraces = false)
@NgImportReference(value = "adaptivePlugin", reference = "@fullcalendar/adaptive", wrapValueInBraces = false)

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
@NgImportReference(value = "TemplateRef", reference = "@angular/core")
@NgField("@ViewChild('resourceAreaColumnHeader') resourceAreaColumnHeaderTpl?: TemplateRef<any>;")
@NgField("@ViewChild('resourceAreaColumnCell') resourceAreaColumnCellTpl?: TemplateRef<any>;")
@NgMethod("""
        private applyResourceAreaTemplates(): void {
            if (this.resourceAreaColumnHeaderTpl && this.resourceAreaColumnCellTpl) {
                const cols: any[] = [{
                    field: 'title',
                    headerContent: this.resourceAreaColumnHeaderTpl,
                    cellContent: this.resourceAreaColumnCellTpl
                }];
                this.calendarOptions = {
                    ...this.calendarOptions,
                    resourceAreaColumns: cols
                };
            }
        }
        """)
@NgAfterViewInit("""
        this.applyResourceAreaTemplates();
        """)
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

    // Template flags for Pro (opt-in). Default false so no ng-template markup unless enabled explicitly.
    private boolean enableResourceLabelTemplate;
    private boolean enableResourceAreaHeaderTemplate;
    private boolean enableResourceAreaColumnTemplates;

    public boolean isEnableResourceLabelTemplate() {return enableResourceLabelTemplate;}

    public boolean isEnableResourceAreaHeaderTemplate() {return enableResourceAreaHeaderTemplate;}

    public boolean isEnableResourceAreaColumnTemplates() {return enableResourceAreaColumnTemplates;}

    @SuppressWarnings("unchecked")
    public J setEnableResourceLabelTemplate(boolean enable)
    {
        this.enableResourceLabelTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableResourceAreaHeaderTemplate(boolean enable)
    {
        this.enableResourceAreaHeaderTemplate = enable;
        return (J) this;
    }

    @SuppressWarnings("unchecked")
    public J setEnableResourceAreaColumnTemplates(boolean enable)
    {
        this.enableResourceAreaColumnTemplates = enable;
        return (J) this;
    }

    /**
     * Convenience to enable all Pro templates (restores previous default behavior).
     */
    @SuppressWarnings("unchecked")
    public J enableAllProTemplates()
    {
        this.enableResourceLabelTemplate = true;
        this.enableResourceAreaHeaderTemplate = true;
        this.enableResourceAreaColumnTemplates = true;
        return (J) this;
    }

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

    @Override
    protected void init()
    {
        // Add Pro-specific Angular template slots
        try
        {
            if (enableResourceLabelTemplate)
            {
                NgTemplateElement resourceLabelContent = new NgTemplateElement("resourceLabelContent").withLetArg();
                resourceLabelContent.add("<span class=\"fc-tpl fc-resource-label\">{{ arg?.resource?.title || arg?.resource?.id }}</span>");
                super.add(resourceLabelContent);
            }

            if (enableResourceAreaHeaderTemplate)
            {
                NgTemplateElement resourceAreaHeaderContent = new NgTemplateElement("resourceAreaHeaderContent").withLetArg();
                resourceAreaHeaderContent.add("<span class=\"fc-tpl fc-resource-area-header\">Resources</span>");
                super.add(resourceAreaHeaderContent);
            }

            if (enableResourceAreaColumnTemplates)
            {
                // resource area column templates (to be bound via @ViewChild and options.resourceAreaColumns)
                NgTemplateElement resourceAreaColumnHeader = new NgTemplateElement("resourceAreaColumnHeader").withLetArg();
                resourceAreaColumnHeader.add("<strong class=\"fc-tpl fc-resource-col-header\">{{ arg?.field || 'Col' }}</strong>");
                super.add(resourceAreaColumnHeader);

                NgTemplateElement resourceAreaColumnCell = new NgTemplateElement("resourceAreaColumnCell").withLetArg();
                resourceAreaColumnCell.add("<span class=\"fc-tpl fc-resource-col-cell\">{{ arg?.resource?.extendedProps?.[arg?.field] || arg?.text }}</span>");
                super.add(resourceAreaColumnCell);
            }
        }
        catch (Exception ignored)
        {
            // generation-only; ignore
        }

        super.init();
    }

    protected void registerWebSocketListeners()
    {
        super.registerWebSocketListeners();
        if (!IGuicedWebSocket.isWebSocketReceiverRegistered(getListenerNameResources()))
        {
            IGuicedWebSocket.addWebSocketMessageReceiver(new InitialResourceEventsReceiver(getListenerNameResources(), getClass()));
        }
    }

    private static class InitialResourceEventsReceiver extends WebSocketAbstractCallReceiver<InitialResourceEventsReceiver>
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
        public io.smallrye.mutiny.Uni<AjaxResponse<?>> action(AjaxCall<?> call, AjaxResponse<?> response)
        {
            return io.smallrye.mutiny.Uni.createFrom()
                                         .item(() -> {
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
                                         });
        }
    }
}
