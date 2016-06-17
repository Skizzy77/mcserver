package com.broaderator.mcserver.kernelcore.bukkit;

import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;

public class ListenerManager extends Module {
    public final String name = "ListenerManager";
    public final Function<Boolean> init = new Function<Boolean>() {
        public Boolean run() {
            return null;
        }
    };
    public final Function<Boolean> exit = new Function<Boolean>() {
        public Boolean run() {
            return null;
        }
    };
    public final String[] dependencies = {};
}
