package com.jwebmp.plugins.fullcalendarpro;

import com.guicedee.guicedinjection.GuiceContext;
import com.jwebmp.core.base.angular.client.annotations.angular.NgComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.IComponent;
import com.jwebmp.core.base.angular.client.services.interfaces.INgApp;
import com.jwebmp.core.base.angular.client.services.interfaces.INgComponent;
import com.jwebmp.core.base.angular.services.compiler.JWebMPTypeScriptCompiler;
import com.jwebmp.core.base.html.DivSimple;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.jwebmp.core.base.angular.client.services.interfaces.AnnotationUtils.getTsFilename;

@NgComponent(value = "full-calendar-example")
public class FullCalendarExample extends DivSimple<FullCalendarExample>
        implements INgComponent<FullCalendarExample>
{
    @Override
    protected void init()
    {
        add(new FullCalendarProComponentExample());
        super.init();
    }

    @Test
    public void testAppSearch() throws IOException, InterruptedException
    {
        GuiceContext.instance()
                .inject();
        for (INgApp<?> app : JWebMPTypeScriptCompiler.getAllApps())
        {
            System.out.println("Test Generating @NgApp (" + getTsFilename(app.getClass()) + ") " +
                    "in folder " + IComponent.getClassDirectory(app.getClass()));
            System.out.println("================");
            //	compiler.renderAppTS(app);
            System.out.println("================");

            JWebMPTypeScriptCompiler compiler = new JWebMPTypeScriptCompiler(app);
        }
        TimeUnit.SECONDS.sleep(2L);
    }
}
