package com.broaderator.mcserver.kernelcore.moduleBase;

import com.broaderator.mcserver.kernelcore.*;
import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/*
Module arguments here are declared final for explicit indication of reference-only entity.
 */
public class ModuleUtils {
    private static final boolean USE_ANTI_RECURSION = true;
    private static List<Module> registerQueue = new LinkedList<>();
    private static List<Module> modulePool = new ArrayList<>();

    private static boolean moduleValid(final String name){
        return ((HashMap<String, Object>) $.globalVolNS.get("Modules")).containsKey(name);
    }

    private static Module getModuleInQueue(final String name){
        for(Module m : registerQueue){
            if (_.getAttribute(m, "name").equals(name)) return m;
        }
        return null;
    }

    public static Module getModule(String name) {
        if (moduleInQueue(name))
            return getModuleInQueue(name);
        if (moduleValid(name)) {
            for (Module m : modulePool) {
                if (_.getAttribute(m, "name").equals(name))
                    return m;
            }
            Logger.error(KCResources.Object, "Unclean module initialization: " + name);
        }
        return null;
    }

    public static Event getEventByOwner(final String moduleName, final String eventName) {
        return getEvent(getModule(moduleName), eventName);
    }

    private static boolean moduleInQueue(final String name){
        return getModuleInQueue(name) != null;
    }

    private static String getPath(final String moduleName){
        return Namespace.joinPath("Modules", moduleName);
    }

    public static boolean registerEvents(final Module m, final String... names){
        if (!moduleValid(((String) _.getAttribute(m, "name")))) {
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to register an event while not registered", ((String) _.getAttribute(m, "name"))));
            return false;
        }
        for(String str : names){
            assert (str != null);
            Logger.debug(KCResources.Object, StringFormat.f("Inserting event for module '{0}': {1}", _.getAttribute(m, "name"), str), $.DL_DETAILS);
            ((HashMap<String, Object>) $.globalVolNS.getSubdirectory(getPath(((String) _.getAttribute(m, "name"))), "Events")).put(str, new Event(str));
        }
        return true;
    }

    public static Event getEvent(final Module m, final String name){
        if (!moduleValid(((String) _.getAttribute(m, "name")))) {
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to register an event while not registered", ((String) _.getAttribute(m, "name"))));
            return null;
        }
        Object ev = $.globalVolNS.getSubdirectory(getPath(((String) _.getAttribute(m, "name"))), "Events", name);
        if (ev instanceof Event) {
            return (Event) ev;
        } else {
            Logger.warn(KCResources.Object, StringFormat.f("Module by the name of '{0}' is trying to get a nonexistient event named '{1}'",
                    _.getAttribute(m, "name"), name));
            return null;
        }
    }

    public static boolean registerKernelCall(final Module m, final Function<? extends Object> run, String name) {
        if (!moduleValid(((String) _.getAttribute(m, "name")))) {
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to register a kernel call while not registered", ((String) _.getAttribute(m, "name"))));
            return false;
        }
        if(((HashMap<String, Object>)$.globalVolNS.get("Calls")).containsKey(name)){
            Logger.warn(KCResources.Object, StringFormat.f("Module by the name of '{0}' has failed to register a kernel call named '{1}' because it was occupied.",
                    _.getAttribute(m, "name"), name));
            return false;
        }else{
            $.globalVolNS.put("Calls." + name, new Tuple<KernelObject, Function<?>>(m, run));
            Logger.debug(KCResources.Object, StringFormat.f("Module by the name of '{0}' has registered a kernel call named '{1}'", _.getAttribute(m, "name"), name), $.DL_DETAILS);
            return true;
        }
    }

    // Attributes
    public static Object getAttribute(final Module m, String label) {
        if (!moduleValid(((String) _.getAttribute(m, "name")))) {
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to get an attribute while not registered", ((String) _.getAttribute(m, "name"))));
            return null;
        }
        return $.globalVolNS.getSubdirectory(getPath(((String) _.getAttribute(m, "name"))), "Resources", label);
    }

    public static boolean setAttribute(final Module m, String label, Object value) {
        if (!moduleValid(((String) _.getAttribute(m, "name")))) {
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to set an attribute while not registered", ((String) _.getAttribute(m, "name"))));
            return false;
        }
        return $.globalVolNS.setSubdirectory(value, getPath(((String) _.getAttribute(m, "name"))), "Resources", label);
    }

    public static boolean hasAttribute(final Module m, String name) {
        return getAttribute(m, name) != null;
    }

    public static Object getOption(final Module m, String name) {
        if (!moduleValid(((String) _.getAttribute(m, "name")))) {
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to get an option while not registered", ((String) _.getAttribute(m, "name"))));
            return null;
        }
        return $.globalNS.getSubdirectory("Modules", ((String) _.getAttribute(m, "name")), "Options", name);
    }

    public static boolean setOption(final Module m, String name, Object value) {
        if (!moduleValid(((String) _.getAttribute(m, "name")))) {
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to set an option while not registered", ((String) _.getAttribute(m, "name"))));
            return false;
        }
        return $.globalNS.setSubdirectory(value, "Modules", ((String) _.getAttribute(m, "name")), "Options", name);
    }

    public static boolean hasOption(final Module m, String name) {
        return getOption(m, name) != null;
    }

    public static boolean addToRegisterQueue(final Module m) {
        if(registerQueue.contains(m)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to add itself to the register queue twice", ((String) _.getAttribute(m, "name"))));
            return false;
        }
        if($.globalVolNS.get("KernelInitialized").equals(true)){
            Logger.error(KCResources.Object,
                    StringFormat.f(
                            "Module by the name of '{0}' is trying to add itself to the queue after kernel initialization", ((String) _.getAttribute(m, "name"))));
            return false;
        }
        registerQueue.add(m);
        return true;
    }

    private static boolean register(final Module m) {
        // check/do dependencies, then do init (using trace or anti-recursion algorithm)
        final ConcurrentLinkedQueue<String> trace;
        final Thread trackerThread;
        final ReentrantLock lock;
        final LinkedList<String> stack;
        if(!USE_ANTI_RECURSION) {
            trace = new ConcurrentLinkedQueue<>();
            trace.add(((String) _.getAttribute(m, "name")));
            lock = new ReentrantLock();
            trackerThread = new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep(10);
                            if(trace.size() > 40) {
                                String temp = "";
                                int t = 0;
                                Iterator<String> iterator = trace.iterator();
                                while(t < 5){
                                    temp += "  -> " + iterator.next() + "\n";
                                    t++;
                                }
                                temp += " -> ... (" + String.valueOf(trace.size() - 5) + " items not shown)";
                                iterator = null;
                                Logger.warn(KCResources.Object, "Module registration recursion detected!\nStack Trace:\n" + temp);
                                lock.lock();
                                break;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },"ModuleRegistrationWatcher");
            trackerThread.start();
            return recurse(((String) _.getAttribute(m, "name")), null, trace, lock);
        }else{
            stack = new LinkedList<>();
            return recurse(((String) _.getAttribute(m, "name")), stack, null, null);
        }
    }

    private static boolean recurse(final String mname, final List<String> trace, final ConcurrentLinkedQueue<String> queue, final ReentrantLock l){
        if(moduleValid(mname)) return true;
        if(!moduleInQueue(mname)){
            Logger.error(KCResources.Object, "Null module reference: " + mname);
            return true;
        }
        Logger.debug
                (KCResources.Object,
                        StringFormat.f(
                                "Calling recursive dependency-initialization function with args {0}, {1}, and {2}", mname, trace, queue),
                        (short)($.DL_DETAILS+1));
        if(USE_ANTI_RECURSION){
            trace.add(mname);
            for (String dep : ((String[]) _.getAttribute(getModuleInQueue(mname), "dependencies"))) {
                if(queue.contains(dep) && !moduleValid(dep)){
                    Logger.error(KCResources.Object, "Infinite recursion error! Recursion loop on module " + dep);
                    return false;
                }
                if(!recurse(dep, trace, null, null)) return false;
            }
        }else{
            queue.offer(mname);
            for (String dep : ((String[]) _.getAttribute(getModuleInQueue(mname), "dependencies"))) {
                if(l.isLocked()) return false;
                if(!recurse(dep, null, queue, l)) return false;
            }
        }
        $.globalVolNS.createDirs(Namespace.joinPath("Modules", mname, "Resources"));
        $.globalVolNS.createDirs(Namespace.joinPath("Modules", mname, "Events"));
        $.globalNS.createDirs(Namespace.joinPath("Modules", mname, "Options"));
        if (((Function<Boolean>) _.getAttribute(getModuleInQueue(mname), "init")).run()) {
            Logger.fine(KCResources.Object, "Kernel module initialized: " + mname);
        }else{
            Logger.error(KCResources.Object, "Kernel module initialization failure! Instability may occur: " + mname);
        }
        registerQueue.remove(getModuleInQueue(mname));
        modulePool.add(getModuleInQueue(mname));
        return true;
    }

    public static void init() {
        while(registerQueue.size() > 0){
            register(registerQueue.get(0));
        }
    }
}
