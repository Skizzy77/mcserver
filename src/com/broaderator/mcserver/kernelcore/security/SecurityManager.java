package com.broaderator.mcserver.kernelcore.security;


import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;

public class SecurityManager extends Module {
    public final String name = "SecurityManager";
    public final Function<Boolean> init = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            return null;
        }
    };
    public final Function<Boolean> exit = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            return null;
        }
    };
    public final String[] dependencies = {"CommandManager", "ListenerManager"};
}
