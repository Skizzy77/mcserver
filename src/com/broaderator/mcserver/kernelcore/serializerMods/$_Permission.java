package com.broaderator.mcserver.kernelcore.serializerMods;

import com.broaderator.mcserver.kernelcore.Serializer;
import com.broaderator.mcserver.kernelcore.security.Permission;

import java.util.HashMap;

public class $_Permission implements Serializer.Module {

    @Override
    public Class<?> getType() {
        return Permission.class;
    }

    @Override
    public Object deserialize(HashMap<String, Object> obj) {
        Permission
    }

    @Override
    public HashMap<String, Object> serialize(Object obj) {
        return null;
    }

    @Override
    public HashMap<String, Class> expectedMap() {
        return null;
    }
}
