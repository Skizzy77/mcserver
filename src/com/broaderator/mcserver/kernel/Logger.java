package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernelbase.KernelObject;
import org.bukkit.Bukkit;

public class Logger {
    private static String identifier = "Kernel";

    // Independent from global namespace, because namespace relies on this. Therefore static.
    public static void warn(KernelObject source, String msg){
        Bukkit.getLogger().warning(identifier + "::" + source.getComponentName() + " [WARN] -> " + msg);
    }

    public static void error(KernelObject source, String msg){
        Bukkit.getLogger().severe(identifier + "::" + source.getComponentName() + " [ERR] -> " + msg);
    }

    public static void info(KernelObject source, String msg){
        Bukkit.getLogger().info(identifier + "::" + source.getComponentName() + " [INFO] -> " + msg);
    }

    public static void fine(KernelObject source, String msg){
        Bukkit.getLogger().fine(identifier + "::" + source.getComponentName() + " [FINE] -> " + msg);
    }

    public static void debug(KernelObject source, String msg, short debugLevel){
        if(debugLevel <= (short) ObjectManager.globalNamespace.get("Logging.DebugLevel")){
            Bukkit.getLogger().finer(identifier + "::" + source.getComponentName() + " [DEBUG] -> " + msg);
        }
    }
}
