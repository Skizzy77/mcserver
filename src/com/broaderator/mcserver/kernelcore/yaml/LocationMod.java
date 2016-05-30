package com.broaderator.mcserver.kernelcore.yaml;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class LocationMod implements YAMLModule<Location> {
    @Override
    public HashMap<String, Object> toYAML(Location obj) {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("x", String.valueOf(obj.getX()));
        hm.put("y", String.valueOf(obj.getY()));
        hm.put("z", String.valueOf(obj.getZ()));
        hm.put("yaw", String.valueOf(obj.getYaw()));
        hm.put("pitch", String.valueOf(obj.getPitch()));
        hm.put("world", obj.getWorld().getUID().toString());
        return hm;
    }

    @Override
    public Class<?> getType() {
        return Location.class;
    }

    @Override
    public Location fromYAML(HashMap<String, Object> obj) {
        return new Location(Bukkit.getWorld(UUID.fromString((String) obj.get("world"))),
                Double.valueOf((String) obj.get("x")),
                Double.valueOf((String) obj.get("y")),
                Double.valueOf((String) obj.get("z")),
                Float.valueOf((String) obj.get("yaw")),
                Float.valueOf((String) obj.get("pitch")));
    }
}
