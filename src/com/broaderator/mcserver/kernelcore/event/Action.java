package com.broaderator.mcserver.kernelcore.event;

import java.util.HashMap;

public class Action {
    public boolean proceed = true;
    public HashMap<String, Object> attributes = new HashMap<>();

    public Action(HashMap<String, Object> attr) {
        this.attributes = attr;
    }
}
