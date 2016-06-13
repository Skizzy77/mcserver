package com.broaderator.mcserver.kernelcore.event;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.KCResources;
import com.broaderator.mcserver.kernelcore.KernelObject;

import java.util.Collection;
import java.util.LinkedList;

public class Event extends LinkedList<EventProcessor<Action>> {

    public Event(){
        super();
    }

    public Event(Collection<EventProcessor<Action>> actions){
        super();
        this.addAll(actions);
    }

    public Action call(KernelObject src, Action obj) {
        Logger.debug(KCResources.Object, "Event called from " + src.getComponentName(), $.DL_INFO);
        for (EventProcessor<Action> psr : this) {
            obj = psr.run(obj);
            Logger.debug(KCResources.Object, "Called EventProcessor", $.DL_DETAILS);
        }
        return obj;
    }
}
