package com.broaderator.mcserver.kernelcore.proc;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.KMI;
import com.broaderator.mcserver.kernelcore.ModuleAgent;
import com.broaderator.mcserver.kernelcore.ModuleResources;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProcessManager {
    static LinkedList<Process> ProcessRegistry = new LinkedList<>();

    static ModuleAgent Ma = new ModuleAgent() {
        @Override
        public String getComponentName() {
            return null;
        }

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
            return true;
        }

        @Override
        public boolean useEvents() {
            return true;
        }

        @Override
        public HashMap<String, Event> getEvents() {
            return new HashMap<String, Event>() {{
                put("RegisterProcess", new Event());
                put("DeregisterProcess", new Event());
                put("InterruptProcess", new Event());
                put("KillProcess", new Event());
            }};
        }
    };
    private static ModuleResources mr = KMI.registerModule(Ma);

    static int _register(Process p) {
        for (Process process : ProcessRegistry) {
            if (process.getName().equals(p.getName())) {
                Logger.error(Ma, StringFormat.f("{0}: Duplicate process with same name found, aborting registration", p.getName()));
                return 1;
            }
        }
        mr.getEvent("RegisterProcess").call(Ma, p);
        ProcessRegistry.offerLast(p);
        Logger.debug(Ma, "Process registered: " + p.getName() + " by " + p.owner.getComponentName(), $.DL_DETAILS);
        return 0;
    }
}
