package com.jwebmp.plugins.fullcalendarpro.implementations;

import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleInclusions;

import java.util.Set;

public class FullCalendarProModuleScanInclusion implements IGuiceScanModuleInclusions<FullCalendarProModuleScanInclusion>
{
    @Override
    public Set<String> includeModules()
    {
        return Set.of("com.jwebmp.plugins.fullcalendarpro");
    }
}
