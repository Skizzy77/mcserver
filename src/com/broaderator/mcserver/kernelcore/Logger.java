package com.broaderator.mcserver.kernelcore;

import org.bukkit.Bukkit;

import java.util.ArrayList;

public class Logger {
    public static ArrayList<String> logOfLog = new ArrayList<>(20);
    // Format: %ident% -> Identifier, %compname% -> Component Name, %level% -> DebugLevel, %msg% -> Message

    public static void warn(KernelObject source, String msg){
        Bukkit.getLogger().warning(_(source, msg, "warn"));
        logOfLog.add("[WARN] " + msg);
        logOfLog.trimToSize();
    }

    public static void error(KernelObject source, String msg){
        Bukkit.getLogger().warning(_(source, msg, "error"));
        logOfLog.add("[ERROR] " + msg);
        logOfLog.trimToSize();
    }

    public static void info(KernelObject source, String msg){
        Bukkit.getLogger().info(_(source, msg, "info"));
        logOfLog.add("[INFO] " + msg);
        logOfLog.trimToSize();
    }

    public static void fine(KernelObject source, String msg){
        Bukkit.getLogger().fine(_(source, msg, "fine"));
        logOfLog.add("[FINE] " + msg);
        logOfLog.trimToSize();
    }

    public static void debug(KernelObject source, String msg, short debugLevel){
        if (debugLevel <= (short) $.globalNS.get("Logging.DebugLevel")) {
            Bukkit.getLogger().config(_(source, msg, "debug"));
            logOfLog.add("[DEBUG] " + msg);
            logOfLog.trimToSize();
        }
    }

    private static String _(KernelObject source, String msg, String level) {
        String format = ((String) $.globalNS.get("Logging.Format"));
        format = format.replaceAll("%ident%", ((String) $.globalNS.get("Resources.KernelName")));
        format = format.replaceAll("%compname%", source.getComponentName());
        format = format.replaceAll("%level%", level);
        format = format.replaceAll("%msg%", msg);
        return format;
    }
}
