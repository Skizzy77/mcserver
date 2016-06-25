package com.broaderator.mcserver.kernelcore.serializerMods;

import com.broaderator.mcserver.kernelcore.Namespace;
import com.broaderator.mcserver.kernelcore.Serializer;
import com.broaderator.mcserver.kernelcore._;
import com.broaderator.mcserver.kernelcore.user.User;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class $_User implements Serializer.Module {

    @Override
    public Class<?> getType() {
        return User.class;
    }

    @Override
    public Object deserialize(HashMap<String, Object> obj) {
        return new User(Bukkit.getOfflinePlayer(UUID.fromString((String) obj.get("UUID"))),
                new Namespace(obj.get("Name") + ".Namespace", true, (HashMap<String, Object>) obj.get("Namespace")),
                new Namespace(obj.get("Name") + ".VolNamespace", false));
    }

    @Override
    public HashMap<String, Object> serialize(Object obj) {
        //assert isValid(obj);
        User u = (User) obj;
        return _.createHashmap("Namespace", u.getNamespace().storage, "UUID", u.asPlayer().getUniqueId().toString(), "Name", u.asPlayer().getName());
    }

    @Override
    public HashMap<String, Class> expectedMap() {
        return new HashMap<String, Class>() {{
            put("Namespace", HashMap.class);
            put("UUID", String.class);
            put("Name", String.class);
        }};
    }
}
