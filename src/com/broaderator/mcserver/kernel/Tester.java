package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernel.yaml.YAMLModule;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;

/* Not intended to be executed in server */
public class Tester {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/michael/Codings/locationMod.mod"));
        oos.writeObject(new YAMLModule<Location>() {
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
            public Location fromYAML(HashMap<String, Object> obj) {
                return new Location(Bukkit.getWorld(UUID.fromString((String) obj.get("world"))),
                        Double.valueOf((String) obj.get("x")),
                        Double.valueOf((String) obj.get("y")),
                        Double.valueOf((String) obj.get("z")),
                        Float.valueOf((String) obj.get("yaw")),
                        Float.valueOf((String) obj.get("pitch")));
            }
        });
        oos.flush();
        oos.close();
    }
}
