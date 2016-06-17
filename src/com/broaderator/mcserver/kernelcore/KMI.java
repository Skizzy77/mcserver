package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.event.Event;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.HashMap;
import java.util.Map;

public class KMI {
    private static HashMap<String, Object> moduleRegistry = $.globalVolNS.getDirectory("Modules");

    public static ModuleResources registerModule(final ModuleAgent ma) {
        if (moduleRegistry.containsKey(ma.getComponentName())) {
            Logger.info(KCResources.Object, "Duplicate registerModule() attempt on module " + ma.getComponentName());
            return (ModuleResources) moduleRegistry.get(ma.getComponentName());
        } else {
            for (ModuleAgent Ma : ma.getDependencies()) {
                if (Ma.getDependencies().contains(ma)) {
                    Logger.error(KCResources.Object, "Recursive dependency for moduleBase: " +
                            ma.getComponentName() + ", " + Ma.getComponentName());
                    return null;
                }
                registerModule(Ma);
            }
            String nspath = Namespace.joinPath("Modules", ma.getComponentName());
            moduleRegistry.put(ma.getComponentName(), new HashMap<String, Object>());
            $.globalVolNS.put(Namespace.joinPath(nspath, "ResourceObject"), new ModuleResources() {
                @Override
                public String getNamespace() {
                    return Namespace.joinPath("Modules", ma.getComponentName());
                }

                @Override
                public void defineEvent(String name, Event e) {
                    $.globalVolNS.getDirectory(Namespace.joinPath(getNamespace(), "Events")).put(name, e);
                }

                @Override
                public Event getEvent(String name) {
                    return (Event) $.globalVolNS.getDirectory(Namespace.joinPath(getNamespace(), "Events")).get(name);
                }
            });
            if (ma.useEvents())
                $.globalVolNS.createDirs(Namespace.joinPath(nspath, "Events"));
            if (ma.useVariables())
                $.globalVolNS.createDirs(Namespace.joinPath(nspath, "Resources"));
            int initResult = ma.init();
            if (!ma.getEvents().isEmpty() && !ma.useEvents())
                Logger.warn(KCResources.Object, StringFormat.f("{0}: Attempt to define events without permission"));
            if (ma.useEvents()) {
                for (Map.Entry<String, Event> entry : ma.getEvents().entrySet()) {
                    $.globalVolNS.getDirectory(
                            Namespace.joinPath("Modules", ma.getComponentName(), "Events"))
                            .put(entry.getKey(), entry.getValue());
                    Logger.debug(KCResources.Object,
                            StringFormat.f("{0}: {1}: Event defined", ma.getComponentName(), entry.getKey()),
                            $.DL_DETAILS);
                }
            }
            Logger.info(KCResources.Object, StringFormat.f("[ {0} ] {1} Module Initialized",
                    initResult, ma.getComponentName()));
            if (initResult != 0) {
                Logger.warn(KCResources.Object, "Non-0 status on finished module initialization");
            }
            return (ModuleResources) moduleRegistry.get(ma.getComponentName());
        }
    }

    public static boolean init() {
        return $.globalVolNS.createDirs("Modules");
    }
}
