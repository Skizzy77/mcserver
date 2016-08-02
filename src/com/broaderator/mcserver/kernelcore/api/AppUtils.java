package com.broaderator.mcserver.kernelcore.api;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore._;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

public class AppUtils {
    public static String getResourcesDirectory(App caller) {
        return StringFormat.f("Apps.{0}.Resources", _.getAttribute(caller, "name"));
    }

    public static String getCommandDirectory(App caller, String commandLabel) {
        return StringFormat.f("Apps.{0}.Commands.{1}", _.getAttribute(caller, "name"), commandLabel);
    }

    public static String getListenerDirectory(App caller, String listenerId) {
        return StringFormat.f("Apps.{0}.Listeners.{1}", _.getAttribute(caller, "name"), listenerId);
    }

    public static String getTaskDirectory(App caller, String taskId) {
        return StringFormat.f("Apps.{0}.Tasks.{1}", _.getAttribute(caller, "name"), taskId);
    }

    public static boolean setCommandAttribute(App caller, String commandLabel, String key, Object val) {
        return $.globalVolNS.put(StringFormat.f("Apps.{0}.Commands.{1}.{2}", _.getAttribute(caller, "name"), commandLabel, key), val);
    }

    public static boolean setCommandOption(App caller, String commandLabel, String key, Object val) {
        return $.globalNS.put(StringFormat.f("Apps.{0}.Commands.{1}.{2}", _.getAttribute(caller, "name"), commandLabel, key), val);
    }

    public static boolean setListenerAttribute(App caller, String commandLabel, String key, Object val) {
        return $.globalVolNS.put(StringFormat.f("Apps.{0}.Listeners.{1}.{2}", _.getAttribute(caller, "name"), commandLabel, key), val);
    }

    public static boolean setListenerOption(App caller, String commandLabel, String key, Object val) {
        return $.globalNS.put(StringFormat.f("Apps.{0}.Listeners.{1}.{2}", _.getAttribute(caller, "name"), commandLabel, key), val);
    }

    public static boolean setTaskAttribute(App caller, String commandLabel, String key, Object val) {
        return $.globalVolNS.put(StringFormat.f("Apps.{0}.Tasks.{1}.{2}", _.getAttribute(caller, "name"), commandLabel, key), val);
    }

    public static boolean setTaskOption(App caller, String commandLabel, String key, Object val) {
        return $.globalNS.put(StringFormat.f("Apps.{0}.Tasks.{1}.{2}", _.getAttribute(caller, "name"), commandLabel, key), val);
    }
}
