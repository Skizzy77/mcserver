package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.event.Event;

public interface ModuleResources {
    String getNamespace();

    void defineEvent(String name, Event event);

    Event getEvent(String name);
}
