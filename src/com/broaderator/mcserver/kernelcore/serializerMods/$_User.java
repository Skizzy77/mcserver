package com.broaderator.mcserver.kernelcore.serializerMods;

import com.broaderator.mcserver.kernelcore.Serializer;
import com.broaderator.mcserver.kernelcore._;
import com.broaderator.mcserver.kernelcore.user.User;

import java.util.HashMap;

public class $_User implements Serializer.Module {

    @Override
    public boolean isValid(Object obj) {
        return obj instanceof User;
    }

    @Override
    public Object serialize(HashMap<String, Object> obj) {

    }

    @Override
    public HashMap<String, Object> deserialize(Object obj) {
        assert isValid(obj);
        User u = (User) obj;
        return _.createHashmap("Namespace", u.getNamespace(), "UUID", u.asPlayer().getUniqueId().toString());// FIXME: 6/25/16
    }
}
