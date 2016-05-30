package com.broaderator.mcserver.kernelcore;

import org.bukkit.Bukkit;

public class Logger {
    // Format: %ident% -> Identifier, %compname% -> Component Name, %level% -> DebugLevel, %msg% -> Message

    public static void warn(KernelObject source, String msg){
        Bukkit.getLogger().warning(_(source, msg));
    }

    public static void error(KernelObject source, String msg){
        Bukkit.getLogger().warning(_(source, msg));
    }

    public static void info(KernelObject source, String msg){
        Bukkit.getLogger().info(_(source, msg));
    }

    public static void fine(KernelObject source, String msg){
        Bukkit.getLogger().fine(_(source, msg));
    }

    public static void debug(KernelObject source, String msg, short debugLevel){
        if (debugLevel <= (short) $.globalNS.get("Logging.DebugLevel")) {
            Bukkit.getLogger().config(_(source, msg));
        }
    }

    private static String _(KernelObject source, String msg) {
        String format = ((String) $.globalNS.get("Logging.Format"));
        format = format.replaceAll("%ident%", ((String) $.globalNS.get("Resources.KernelName")));
        format = format.replaceAll("%compname%", source.getComponentName());
        format = format.replaceAll("%level%", "DEBUG");
        format = format.replaceAll("%msg%", msg);
        return format;
    }
}
