package com.broaderator.mcserver.kernelcore.event;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.KCResources;
import com.broaderator.mcserver.kernelcore.KernelObject;

import java.util.Collection;
import java.util.LinkedList;

public class Event extends LinkedList<EventProcessor<Action>> implements KernelObject {
    private String name;

    public Event(String name){
        super();
        this.name = name;
    }

    public Event(String name, Collection<EventProcessor<Action>> actions){
        super();
        this.addAll(actions);
        this.name = name;
    }

    public Action call(KernelObject src, Action obj) {
        Logger.debug(KCResources.Object, "Event called from " + src.getComponentName(), $.DL_INFO);
        for (EventProcessor<Action> psr : this) {
            obj = psr.run(obj);
            Logger.debug(KCResources.Object, "Called EventProcessor", $.DL_DETAILS);
        }
        return obj;
    }

    @Override
    public String getComponentName() {
        return name;
    }
}
