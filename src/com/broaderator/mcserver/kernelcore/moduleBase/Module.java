package com.broaderator.mcserver.kernelcore.moduleBase;


import com.broaderator.mcserver.kernelcore.KernelObject;

public abstract class Module implements KernelObject {
    public String name;
    public Function<Boolean> init;
    public Function<Boolean> exit;
    public String[] dependencies;

    @Override
    public String getComponentName() { return name; }
}
