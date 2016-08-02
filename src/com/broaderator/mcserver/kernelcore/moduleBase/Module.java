package com.broaderator.mcserver.kernelcore.moduleBase;


import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore._;

public abstract class Module implements KernelObject {
    public final Module This = this;
    /*public String name;
    public Function<Boolean> init;
    public Function<Boolean> exit;
    public String[] dependencies;*/

    @Override
    public String getComponentName() {
        return String.valueOf(_.getAttribute(this, "name"));
    }
}
