package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelbase.KernelObject;

import java.util.List;

public interface ModuleAgent extends KernelObject {
    int init();

    int exit();

    List<ModuleAgent> getDependencies();

    boolean useVariables();

    boolean useEvents();
}
