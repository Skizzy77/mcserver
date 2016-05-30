package com.broaderator.mcserver.kernelcore.yaml;

import com.broaderator.mcserver.kernelcore.KMI;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernel.loader.LoadPriority;
import com.broaderator.mcserver.kernel.loader.LoadPriorityLevel;
import com.broaderator.mcserver.kernelcore.ModuleAgent;
import com.broaderator.mcserver.kernelcore.ModuleResources;
import com.broaderator.mcserver.kernelcore.event.Action;
import com.broaderator.mcserver.kernelcore.event.Event;

import java.io.Serializable;
import java.util.*;

public class YAMLManager {
    private static final String nsRoot = "Manager.YAML";
    private static List<YAMLModule<?>> modules = Arrays.asList(
            new LocationMod(),
            new UserMod()
    );
    public static final ModuleAgent Ma = new ModuleAgent() {
        @Override
        public int init() {
            return 0;
        }

        @Override
        public int exit() {
            modules.clear();
            return 0;
        }

        @Override
        public List<ModuleAgent> getDependencies() {
            return Collections.emptyList();
        }

        @Override
        public boolean useVariables() {
            return false;
        }

        @Override
        public boolean useEvents() {
            return true;
        }

        @Override
        public HashMap<String, Event> getEvents() {
            return new HashMap<String, Event>() {{
                put("EncodeToYAML", new Event());
                put("DecodeFromYAML", new Event());
                put("UseYAMLModules", new Event());
            }};
        }

        @Override
        public String getComponentName() {
            return "YAMLManager";
        }


    };
    static final ModuleResources mr = KMI.registerModule(Ma);

    private static final List<Class<? extends Serializable>> validClasses = Arrays.asList(
            String.class,
            ArrayList.class,
            HashMap.class,
            Integer.class,
            Boolean.class,
            Long.class,
            Character.class
    );

    private static boolean _isValidClass(Object obj) {
        for (Class c : validClasses) {
            if (c.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    public static Object toRepresentation(final Object input) {
        if (_isValidClass(input)) return input;
        for (final YAMLModule module : modules) {
            boolean proceed = mr.getEvent("UseYAMLModules").call(Ma, new Action(new HashMap<String, Object>() {{
                put("YamlModule", module);
            }})).proceed;
            if (module.getType().isInstance(input) && proceed) {
                // attributes are intended to be read-only
                Action result = mr.getEvent("EncodeToYAML").call(Ma, new Action(new HashMap<String, Object>() {{
                    put("YamlModule", module);
                    put("InputObject", input);
                    put("InputType", module.getType());
                }}));
                if (result.proceed)
                    return module.toYAML(input);
            }
        }
        Logger.warn(Ma, "Failed to convert YAML-incompatible object: " + input);
        return null;
    }

    public static Object fromRepresentation(Object input, Class<?> destClass) {
        if (!(input instanceof HashMap)) {
            if (_isValidClass(input))
                return input;
            else {
                Logger.error(Ma, "Cannot convert fromRepresentation() parameter to valid object");
                return null;
            }
        } else {
            for (YAMLModule module : modules) {
                if (module.getType().equals(destClass) || module.getType() == destClass) {
                    return module.fromYAML((HashMap<String, Object>) input);
                }
            }
        }
        return null;
    }
}
