package com.broaderator.mcserver.kernel.yaml;

import com.broaderator.mcserver.kernel.Logger;
import com.broaderator.mcserver.kernel.loader.LoadPriority;
import com.broaderator.mcserver.kernel.loader.LoadPriorityLevel;
import com.broaderator.mcserver.kernelbase.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class YAMLManager {
    private static final String nsRoot = "Manager.YAML";
    private static List<YAMLModule<?>> modules = Arrays.asList(
            new LocationMod(),
            new UserMod()
    );
    public static final Manager yamlMan = new Manager() {
        @Override
        @LoadPriority(level = LoadPriorityLevel.EMPTY)
        public boolean spawn() {
            return true;
        }

        @Override
        @LoadPriority(level = LoadPriorityLevel.EMPTY)
        public boolean die() {
            modules.clear();
            return true;
        }

        @Override
        public String getComponentName() {
            return "YAMLManager";
        }

        @Override
        public boolean execute(String cmd) {
            return false;
        }
    };

    static final List<Class<? extends Serializable>> validClasses = Arrays.asList(
            String.class,
            ArrayList.class,
            HashMap.class,
            Integer.class,
            Boolean.class,
            Long.class,
            Character.class
    );

    public static boolean isValid(Object obj) {
        for (Class c : validClasses) {
            if (c.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    public static Object toRepresentation(Object input) {
        if (isValid(input)) return input;
        for (YAMLModule module : modules) {
            if (module.getType().isInstance(input)) {
                return module.toYAML(input);
            }
        }
        Logger.warn(yamlMan, "Failed to convert YAML-incompatible object: " + input);
        return null;
    }

    public static Object fromRepresentation(HashMap<String, Object> input, Class<?> destClass) {
        for (YAMLModule module : modules) {
            if (module.getType().equals(destClass) || module.getType() == destClass) {
                return module.fromYAML(input);
            }
        }
        return null;
    }
}
