package com.broaderator.mcserver.kernelcore.security;


import com.broaderator.mcserver.kernelcore.ModuleAgent;
import com.broaderator.mcserver.kernelcore.event.Event;

import java.util.HashMap;
import java.util.List;

public class SecurityManager {
    static ModuleAgent Ma = new ModuleAgent() {
        @Override
        public int init() {
            // assign hash to KernelAuthority

            return 0;
        }

        @Override
        public int exit() {
            return 0;
        }

        @Override
        public List<ModuleAgent> getDependencies() {
            return null;
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
            return null;
        }

        @Override
        public String getComponentName() {
            return "SecurityManager";
        }
    }
}
