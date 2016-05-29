package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernel.$;
import com.broaderator.mcserver.kernel.Logger;
import com.broaderator.mcserver.kernelbase.Namespace;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.HashMap;

public class KMI {
    private static HashMap<String, Object> moduleRegistry = $.globalVolNS.getDirectory("Modules");

    public static ModuleResources registerModule(final ModuleAgent ma) {
        if (moduleRegistry.containsKey(ma.getComponentName())) {
            Logger.info(KCResources.Object, "Duplicate registerModule() attempt on module " + ma.getComponentName());
            return (ModuleResources) moduleRegistry.get(ma.getComponentName());
        } else {
            for (ModuleAgent Ma : ma.getDependencies()) {
                if (Ma.getDependencies().contains(ma)) {
                    Logger.error(KCResources.Object, "Recursive dependency for modules: " +
                            ma.getComponentName() + ", " + Ma.getComponentName());
                    return null;
                }
                registerModule(Ma);
            }
            String nspath = Namespace.joinPath("Modules", ma.getComponentName());
            moduleRegistry.put(ma.getComponentName(), new ModuleResources() {
                @Override
                public String getNamespace() {
                    return Namespace.joinPath("Modules", ma.getComponentName());
                }
            });
            if (ma.useEvents())
                $.globalVolNS.createDirs(Namespace.joinPath(nspath, "Events"));
            if (ma.useVariables())
                $.globalVolNS.createDirs(Namespace.joinPath(nspath, "Resources"));
            int initResult = ma.init();
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
