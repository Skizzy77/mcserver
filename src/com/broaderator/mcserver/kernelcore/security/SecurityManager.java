package com.broaderator.mcserver.kernelcore.security;


import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;
import com.broaderator.mcserver.kernelcore.moduleBase.ModuleUtils;

public class SecurityManager extends Module {
    private final SecurityManager This = this;
    public final String name = "SecurityManager";
    public final Function<Boolean> init = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            ModuleUtils.registerKernelCall(This, new Function<Boolean>() {
            }, "_GetCommandPermission");
        }
    };
    public final Function<Boolean> exit = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            return null;
        }
    };
    public final String[] dependencies = {};
}
