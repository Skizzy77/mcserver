package com.broaderator.mcserver.kernelcore.proc;

import com.broaderator.mcserver.kernelcore.*;
import com.broaderator.mcserver.kernelcore.event.Action;
import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProcessManager {

    static LinkedList<Process> getProcessRegistry() {
        return (LinkedList<Process>) $.globalVolNS.getSubdirectory(mr.getNamespace(), "ProcessRegistry");
    }
    static ModuleAgent Ma = new ModuleAgent() {
        @Override
        public String getComponentName() {
            return "ProcessManager";
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

    static int _register(final Process p) {
        for (Process process : getProcessRegistry()) {
            if (process.getName().equals(p.getName())) {
                Logger.error(Ma, StringFormat.f("{0}: Duplicate process with same name found, aborting registration", p.getName()));
                return 1;
            }
        }
        mr.getEvent("RegisterProcess").call(Ma, new Action(_.createHashmap("Process", p)));
        getProcessRegistry().offerLast(p);
        Logger.debug(Ma, "Process registered: " + p.getName() + " by " + p.owner.getComponentName(), $.DL_DETAILS);
        return 0;
    }

    public static boolean interrupt(int pid){
        for(final Process p : getProcessRegistry()){
            if(p.pid == pid){
                Action result = mr.getEvent("InterruptProcess").call(Ma, new Action(_.createHashmap("Process", p)));
                if(result.proceed){
                    ((Process) result.attributes.get("Process")).interrupt();
                    Logger.debug(Ma, "Interrupted process, " +
                            "PID: " + pid + ", Name: " + p.getName(), $.DL_DETAILS);
                    return true;
                }else{
                    Logger.debug(Ma, "Attempt to interrupt process has been interrupted during Event handling. " +
                            "PID: " + pid + ", Name: " + p.getName(), $.DL_INFO);
                    return false;
                }
            }
        }
        Logger.error(Ma, "Interrupt failed: No process found with PID " + pid);
        return false;
    }
}
