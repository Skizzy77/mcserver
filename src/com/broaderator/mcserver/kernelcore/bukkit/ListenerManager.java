package com.broaderator.mcserver.kernelcore.bukkit;

import com.broaderator.mcserver.kernelcore.ModuleAgent;
import com.broaderator.mcserver.kernelcore.event.Event;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListenerManager {
    static final ModuleAgent Ma = new ModuleAgent() {
        @Override
        public int init() {
            return 0;
        }

        @Override
        public int exit() {
            return 0;
        }

        @Override
        public List<ModuleAgent> getDependencies() {
            return Collections.emptyList();
        }

        @Override
        public boolean useVariables() {
            return false;
        }

        @Override
        public boolean useEvents() {
            return false;
        }

        @Override
        public HashMap<String, Event> getEvents() {
            return new HashMap<String, Event>(){{
                put("PlayerCommandPreprocess", new Event());
                put("PlayerDeath", new Event());
                put("EntityDamage", new Event());
            }};
        }

        @Override
        public String getComponentName() {
            return null;
        }
    }
}
