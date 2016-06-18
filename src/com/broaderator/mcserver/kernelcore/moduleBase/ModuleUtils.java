package com.broaderator.mcserver.kernelcore.moduleBase;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.KCResources;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.Namespace;
import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.HashMap;

/*
Module arguments here are declared final for explicit indication of reference-only entity.
 */
public class ModuleUtils {

    private static boolean moduleValid(final String name){
        return ((HashMap<String, Object>) $.globalVolNS.get("Modules")).containsKey(name);
    }

    private static String getPath(final String moduleName){
        return Namespace.joinPath("Modules", moduleName);
    }

    public static boolean registerEvents(final Module m, final String... names){
        if(!moduleValid(m.name)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to register an event while not registered", m.name));
            return false;
        }
        for(String str : names){
            assert (str != null);
            Logger.debug(KCResources.Object, StringFormat.f("Inserting event for module '{0}': {1}", m.name, str), $.DL_DETAILS);
            ((HashMap<String, Object>) $.globalVolNS.getSubdirectory(getPath(m.name), "Events")).put(str, new Event(str));
        }
        return true;
    }

    public static Event getEvent(final Module m, final String name){
        Object ev = $.globalVolNS.getSubdirectory(getPath(m.name), "Events", name);
        if (ev instanceof Event) {
            return (Event) ev;
        } else {
            Logger.warn(KCResources.Object, StringFormat.f("Module by the name of '{0}' is trying to get a nonexistient event named '{1}'",
                    m.name, name));
            return null;
        }
    }

    public static boolean registerKernelCall(final Module m, final Function<Object> run) {

    }

    // Attributes
    public static Object getAttribute(final Module m, String label) {

    }

    public static boolean setAttribute(final Module m, String label, Object value) {

    }

    public static Object getOption(final Module m, String name) {

    }

    public static boolean addToRegisterQueue(final Module m) {

    }

    private static boolean register(final Module m) {

    }
}
