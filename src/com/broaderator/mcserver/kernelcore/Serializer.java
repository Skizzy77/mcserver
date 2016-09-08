package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.serializerMods.$_User;

import java.util.*;

public class Serializer {
    public interface Module {
        Class<?> getType();

        Object deserialize(HashMap<String, Object> obj);

        HashMap<String, Object> serialize(Object obj);

        HashMap<String, Class> expectedMap();
    }

    private static List<? extends Module> modules = Arrays.asList(
            new $_User()
    );

    private static final List<Class<?>> QUALIFIED_TYPES = Arrays.asList(
            Map.class,
            String.class,
            Set.class,
            Boolean.class,
            Float.class,
            Integer.class,
            Date.class
    );

    private static int _getType(Object o) {
        if (o == null) return 0;
        for (Class type : QUALIFIED_TYPES) {
            if (type.isInstance(o)) return 1;
        }
        if (o instanceof Serializable) {
            return 2;
        }
        for (Module m : modules) {
            if (m.getType().isInstance(o)) return 3 + modules.indexOf(m);
        }
        return -1;
    }

    public static boolean isSerializable(Object obj) {
        return _getType(obj) >= 0;
    }

    public static Object serialize(Object obj) {
        int type = _getType(obj);
        if (type < 0) {
            Logger.warn(KCResources.Object, "Serializer: unable to serialize object of type: " + obj.getClass().getName());
            return null;
        }
        switch (type) {
            case 0:
                return null;
            case 1:
                return obj;
            case 2:
                return ((Serializable) obj).represent();
            default:
                final Module pointer = modules.get(type - 3);
                assert pointer.getType().isInstance(obj);
                return pointer.serialize(obj);
        }
    }

    public static Object deserialize(Object obj, Class<?> targetClass) {
        if (obj == null) return null;
        for (Class type : QUALIFIED_TYPES) {
            if (type.isInstance(obj)) return obj;
        }
        for (Module m : modules) {
            if (m.getType().equals(targetClass)) {
                assert obj instanceof HashMap;
                final HashMap<String, Object> hobj = (HashMap) obj;
                for (Map.Entry<String, Class> entry : m.expectedMap().entrySet()) {
                    if (!hobj.containsKey(entry.getKey())) {
                        Logger.error(KCResources.Object, "Invalid HashMap for deserialization to object of type " + targetClass.getName()
                                + ": Missing map key \"" + entry.getKey() + "\"");
                        return null;
                    }
                    if (!entry.getValue().isInstance(hobj.get(entry.getKey()))) {
                        Logger.error(KCResources.Object, "Invalid HashMap for deserialization to object of type " + targetClass.getName()
                                + ": Bad value type for key \"" + entry.getKey() + "\"" + ": " + hobj.get(entry.getKey()));
                        return null;
                    }
                }
                return m.deserialize(hobj);
            }
        }
        Logger.warn(KCResources.Object, "Deserializer: Failed to deserialize object to class " +
                targetClass.getName() + ": " + obj);
        return null;
    }
}
