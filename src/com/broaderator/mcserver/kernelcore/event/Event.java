package com.broaderator.mcserver.kernelcore.event;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.KCResources;
import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.Logger;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Event extends LinkedList<EventProcessor<Action>> implements KernelObject {
    private static final String PathToHistory = "RecentEvents";

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
        insertHistory();
        return obj;
    }

    private void insertHistory() {
        ((List<String>) $.globalVolNS.get(PathToHistory)).add(this.name);
    }

    @Override
    public String getComponentName() {
        return name;
    }
}
