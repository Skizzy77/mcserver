package com.broaderator.mcserver.kernelcore.proc;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.util.StringFormat;


public class Process extends Thread {
    int pid;
    KernelObject owner;

    public Process(KernelObject origin, Runnable run, String name) {
        super(run);
        this.setName(name);
        this.owner = origin;
        this.pid = ProcessManager._register(this);
        if (this.pid < 0) {
            Logger.error(ProcessManager.Ma, "Failed to register process to manager: " + this.getName());
        } else {
            Logger.debug(ProcessManager.Ma, StringFormat.f("Process registered to ProcessManager: {0} -> {1}",
                    this.getName(), pid), $.DL_DETAILS);
        }
    }

    public Process(KernelObject origin, String name) {
        super();
        this.setName(name);
        this.owner = origin;
        this.pid = ProcessManager._register(this);
        if (this.pid < 0) {
            Logger.error(ProcessManager.Ma, "Failed to register process to manager: " + this.getName());
        } else {
            Logger.debug(ProcessManager.Ma, StringFormat.f("Process registered to ProcessManager: {0} -> {1}",
                    this.getName(), pid), $.DL_DETAILS);
        }
    }

    public int getStatus() {
        return this.isAlive() ? 1 : 0;
    }
}
