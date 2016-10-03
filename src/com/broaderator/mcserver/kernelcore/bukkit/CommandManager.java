package com.broaderator.mcserver.kernelcore.bukkit;

import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;

public class CommandManager extends Module {
    public String name = "CommandManager";
    public Function<Boolean> init = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            return null;
        }
    };
}
