package com.broaderator.mcserver.kernel.yaml;

import com.broaderator.mcserver.kernel.User;
import com.broaderator.mcserver.kernelbase.Namespace;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

public class UserMod implements YAMLModule<User> {

    @Override
    public HashMap<String, Object> toYAML(User obj) {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("uuid", obj.asPlayer().getUniqueId().toString());
        hm.put("name", obj.asPlayer().getName());
        hm.put("namespace", obj.getNamespace().storage);
        return hm;
    }

    @Override
    public Class<?> getType() {
        return User.class;
    }

    @Override
    public User fromYAML(HashMap<String, Object> obj) {
        assert obj.get("namespace") instanceof HashMap;
        return new User(Bukkit.getOfflinePlayer(UUID.fromString((String) obj.get("uuid"))),
                new Namespace(obj.get("name") + ".NS", true, (HashMap<String, Object>) obj.get("namespace")),
                new Namespace(obj.get("name") + ".NSVolatile", false));
    }
}
