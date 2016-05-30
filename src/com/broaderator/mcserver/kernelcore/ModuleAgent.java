package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.event.Event;

import java.util.HashMap;
import java.util.List;

public interface ModuleAgent extends KernelObject {
    int init();

    int exit();

    List<ModuleAgent> getDependencies();

    boolean useVariables();

    boolean useEvents();

    HashMap<String, Event> getEvents();
}
