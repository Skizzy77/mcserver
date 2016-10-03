package com.broaderator.mcserver.kernelcore.proc;

import com.broaderator.mcserver.kernelcore.*;
import com.broaderator.mcserver.kernelcore.event.Action;
import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProcessManager implements KernelObject{

    /*static LinkedList<Process> getProcessRegistry() {
        return (LinkedList<Process>) $.globalVolNS.getSubdirectory(mr.getNamespace(), "ProcessRegistry");
    }*/

    /*static int _register(final Process p) {
        for (Process process : getProcessRegistry()) {
            if (process.getName().equals(p.getName())) {
                Logger.error(KCResources.Object, StringFormat.f("{0}: Duplicate process with same name found, aborting registration", p.getName()));
                return 1;
            }
        }
        *//*mr.getEvent("RegisterProcess").call(KCResources.Object, new Action(_.createHashmap("Process", p)));*//*
        getProcessRegistry().offerLast(p);
        Logger.debug(KCResources.Object, "Process registered: " + p.getName() + " by " + p.owner.getComponentName(), $.DL_DETAILS);
        return 0;
    }*/

    public static boolean interrupt(int pid){
        /*for(final Process p : getProcessRegistry()){
            if(p.pid == pid){
                Action result = mr.getEvent("InterruptProcess").call(KCResources.Object, new Action(_.createHashmap("Process", p)));
                if(result.proceed){
                    ((Process) result.attributes.get("Process")).interrupt();
                    Logger.debug(KCResources.Object, "Interrupted process, " +
                            "PID: " + pid + ", Name: " + p.getName(), $.DL_DETAILS);
                    return true;
                }else{
                    Logger.debug(KCResources.Object, "Attempt to interrupt process has been interrupted during Event handling. " +
                            "PID: " + pid + ", Name: " + p.getName(), $.DL_INFO);
                    return false;
                }
            }
        }*/
        Logger.error(KCResources.Object, "Interrupt failed: No process found with PID " + pid);
        return false;
    }

    @Override
    public String getComponentName() {
        return "ProcessManager";
    }
}
