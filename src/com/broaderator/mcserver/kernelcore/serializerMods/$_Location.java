package com.broaderator.mcserver.kernelcore.serializerMods;

import com.broaderator.mcserver.kernelcore.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class $_Location implements Serializer.Module {

    @Override
    public Class<?> getType() {
        return Location.class;
    }

    @Override
    public Object deserialize(HashMap<String, Object> obj) {
        return new Location(Bukkit.getWorld(UUID.fromString((String) obj.get("World"))),
                (Double) obj.get("X"),
                (Double) obj.get("Y"),
                (Double) obj.get("Z"),
                (float) ((Double) obj.get("Pitch")).doubleValue(),
                (float) ((Double) obj.get("Yaw")).doubleValue());
    }

    @Override
    public HashMap<String, Object> serialize(final Object obj) {
        final Location lobj = (Location) obj;
        return new HashMap<String, Object>() {{
            put("World", lobj.getWorld().getUID().toString());
            put("X", lobj.getX());
            put("Y", lobj.getY());
            put("Z", lobj.getZ());
            put("Pitch", (double) lobj.getPitch());
            put("Yaw", (double) lobj.getYaw());
        }};
    }

    @Override
    public HashMap<String, Class> expectedMap() {
        return new HashMap<String, Class>() {{
            put("World", String.class);
            put("X", Double.class);
            put("Y", Double.class);
            put("Z", Double.class);
            put("Pitch", Double.class);
            put("Yaw", Double.class);
        }};
    }
}
