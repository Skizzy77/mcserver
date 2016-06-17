package com.broaderator.mcserver.kernelcore.moduleBase;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.KCResources;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.Namespace;
import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.HashMap;

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
            Logger.debug(KCResources.Object, StringFormat.f("Inserting event for module '{0}': {1}", m.name, str), $.DL_DETAILS);
            ((HashMap<String, Object>) $.globalVolNS.getSubdirectory(getPath(m.name), "Events")).put(str, new Event(str));
        }
    }

    public static Event getEvent(final Module m, final String name){

    }

    public static boolean registerKernelCall(final Module m, Runnable run){

    }

    // Attributes
    public static Object get(final Module m, String label){

    }

    public static boolean set(final Module m, String label, Object value){

    }

    public static boolean register(final Module m){

    }
}
