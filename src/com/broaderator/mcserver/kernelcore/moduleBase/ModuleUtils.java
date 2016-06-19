package com.broaderator.mcserver.kernelcore.moduleBase;

import com.broaderator.mcserver.kernelcore.*;
import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.ArrayList;
import java.util.HashMap;

/*
Module arguments here are declared final for explicit indication of reference-only entity.
 */
public class ModuleUtils {
    private static ArrayList<Module> registerQueue = new ArrayList<>();

    private static boolean moduleValid(final String name){
        return ((HashMap<String, Object>) $.globalVolNS.get("Modules")).containsKey(name);
    }

    private static boolean moduleInQueue(final String name){
        for(Module m : registerQueue){
            if(m.name.equals(name)) return true;
        }
        return false;
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
        if(!moduleValid(m.name)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to register an event while not registered", m.name));
            return null;
        }
        Object ev = $.globalVolNS.getSubdirectory(getPath(m.name), "Events", name);
        if (ev instanceof Event) {
            return (Event) ev;
        } else {
            Logger.warn(KCResources.Object, StringFormat.f("Module by the name of '{0}' is trying to get a nonexistient event named '{1}'",
                    m.name, name));
            return null;
        }
    }

    public static boolean registerKernelCall(final Module m, final Function<Object> run, String name) {
        if(!moduleValid(m.name)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to register a kernel call while not registered", m.name));
            return false;
        }
        if(((HashMap<String, Object>)$.globalVolNS.get("Calls")).containsKey(name)){
            Logger.warn(KCResources.Object, StringFormat.f("Module by the name of '{0}' has failed to register a kernel call named '{1}' because it was occupied.",
                    m.name, name));
            return false;
        }else{
            $.globalVolNS.put("Calls."+name, new Tuple<KernelObject, Function<Object>>(m, run));
            Logger.debug(KCResources.Object, StringFormat.f("Module by the name of '{0}' has registered a kernel call named '{1}'", m.name, name), $.DL_DETAILS);
            return true;
        }
    }

    // Attributes
    public static Object getAttribute(final Module m, String label) {
        if(!moduleValid(m.name)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to get an attribute while not registered", m.name));
            return null;
        }
        return $.globalVolNS.getSubdirectory(getPath(m.name), "Resources", label);
    }

    public static boolean setAttribute(final Module m, String label, Object value) {
        if(!moduleValid(m.name)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to set an attribute while not registered", m.name));
            return false;
        }
        return $.globalVolNS.setSubdirectory(value, getPath(m.name), "Resources", label);
    }

    public static boolean hasAttribute(final Module m, String name) {
        return getAttribute(m, name) != null;
    }

    public static Object getOption(final Module m, String name) {
        if(!moduleValid(m.name)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to get an option while not registered", m.name));
            return null;
        }
        return $.globalNS.getSubdirectory("Modules", m.name, "Options", name);
    }

    public static boolean setOption(final Module m, String name, Object value) {
        if(!moduleValid(m.name)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to set an option while not registered", m.name));
            return false;
        }
        return $.globalNS.setSubdirectory(value, "Modules", m.name, "Options", name);
    }

    public static boolean hasOption(final Module m, String name) {
        return getOption(m, name) != null;
    }

    public static boolean addToRegisterQueue(final Module m) {
        if(registerQueue.contains(m)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to add itself to the register queue twice", m.name));
            return false;
        }
        if($.globalVolNS.get("KernelInitialized").equals(true)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to add itself to the queue after kernel initialization", m.name));
            return false;
        }
        registerQueue.add(m);
        return true;
    }

    private static boolean register(final Module m) {
        // check/do dependencies, then do init
        for(String dep : m.dependencies){
            if(moduleValid(dep)) continue;
            if(!moduleInQueue(dep)){
                Logger.error(KCResources.Object, StringFormat.f("Invalid dependency of module '{0}', risking registration without dependency initialization: {1}", m.name, dep));
            }else{
                // fixme: Not finished
            }
        }
    }
}
