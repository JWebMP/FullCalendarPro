/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jwebmp.plugins.fullcalendarpro;

import com.jwebmp.core.base.angular.client.annotations.typescript.TsDependency;
import com.jwebmp.core.plugins.PluginInformation;
import com.jwebmp.core.plugins.PluginStatus;
import com.jwebmp.core.services.IPage;
import com.jwebmp.core.services.IPageConfigurator;

/**
 * The 3 meta tags *must* come first in the head; any other head content must come *after* these tags
 * <p>
 * HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries WARNING: Respond.js doesn't work if you view the page via
 * file://
 */
@PluginInformation(pluginName = "Full Calendar",
        pluginUniqueName = "full-calendar-pro",
        pluginDescription = "Full Calendar Pro is the purchased library from the free, open-source project that enables you to design full calendar-like features and functions that is fully interactive, ajax controlled and mobile friendly. ",
        pluginVersion = "6.1.19",
        pluginDependancyUniqueIDs = "jquery,moment",
        pluginCategories = "jquery, calendar, schedular, event planner, events, ui, web",
        pluginSubtitle = "Display a full-size drag-n-drop event calendar, leveraging jQuery. ",
        pluginGitUrl = "https://github.com/GedMarc/JWebMP-FullCalendarPlugin",
        pluginSourceUrl = "https://github.com/fullcalendar",
        pluginWikiUrl = "https://github.com/GedMarc/JWebMP-FullCalendarPlugin/wiki",
        pluginOriginalHomepage = "https://fullcalendar.io/",
        pluginSourceDonateUrl = "https://fullcalendar.io/donate/",
        pluginDownloadUrl = "https://mvnrepository.com/artifact/com.jwebmp.plugins.jquery/jwebmp-full-calendar",
        pluginIconUrl = "",
        pluginIconImageUrl = "",
        pluginLastUpdatedDate = "2025/10/29",
        pluginGroupId = "com.jwebmp.plugins",
        pluginArtifactId = "full-calendar-pro",
        pluginModuleName = "com.jwebmp.plugins.fullcalendarpro",
        pluginStatus = PluginStatus.Released
)

@TsDependency(value = "@fullcalendar/resource-timegrid", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/resource-timeline", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/adaptive", version = "^6.1.19")
@TsDependency(value = "@fullcalendar/resource-daygrid", version = "^6.1.19")

/*

@NgBootImportReference(value = "!resourceDayGridPlugin", reference = "@fullcalendar/resource-daygrid")
@NgBootImportReference(value = "!resourceTimeGridPlugin", reference = "@fullcalendar/resource-timegrid")
@NgBootImportReference(value = "!resourceTimelinePlugin", reference = "@fullcalendar/resource-timeline")
@NgBootImportReference(value = "!adaptivePlugin", reference = "@fullcalendar/adaptive")
*/


/*@NgBootGlobalField("FullCalendarModule.registerPlugins([\n" +
        "  resourceTimeGridPlugin,\n" +
        "  resourceTimelinePlugin,\n" +
        "  resourceDayGridPlugin,\n" +
        "  adaptivePlugin\n" +
        "])")*/

public class FullCalendarProPageConfigurator
        implements IPageConfigurator<FullCalendarProPageConfigurator>
{
    @Override
    public IPage<?> configure(IPage<?> page)
    {
        return page;
    }

    @Override
    public boolean enabled()
    {
        return true;
    }

}
