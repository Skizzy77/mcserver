package com.broaderator.mcserver.core.modules.security;

import java.util.HashMap;

// SecurityEvent, contains a Bukkit event and is only used for security policy result collections (and API)
public class Event<E extends org.bukkit.event.Event> {

    private E bukkitEvent;
    private boolean proceed;
    private HashMap<String, String> properties;

    public Event(
            E bukkitEvent,
            HashMap<String, String> properties) {
        this.bukkitEvent = bukkitEvent;
        this.proceed = true;
        this.properties = properties;
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    public void abort() {
        this.proceed = false;
    }

    public boolean isAborted() {
        return !this.proceed;
    }

    public E getBukkitEvent() {
        return bukkitEvent;
    }

}
