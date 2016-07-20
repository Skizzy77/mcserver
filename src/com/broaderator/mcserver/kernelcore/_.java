package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Random;

public class _ {
    // Utilities

    // Min inclusive, Max exclusive
    public static int randomInt(Random generator, int min, int max) {
        return generator.nextInt(max - min) + min;
    }

    public static String toHexString(byte[] bytes) {
        String output = "";
        for (byte b : bytes) {
            output += String.format("%02x", b);
        }
        return output;
    }

    public static HashMap<String, Object> createHashmap(Object... param){
        assert param.length % 2 == 0;
        HashMap<String, Object> hmap = new HashMap<>();
        for(int i = 0; i < param.length; i+=2){
            hmap.put((String) param[i], param[i+1]);
        }
        return hmap;
    }

    public static Object getAttribute(Object source, String name) {
        try {
            return source.getClass().getDeclaredField(name).get(source);
        } catch (Exception e) {
            Bukkit.getLogger().severe("getAttribute failed, exception attached");
            e.printStackTrace();
            return null;
        }
    }

    public static final Function<Boolean> EmptyFunction = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            return true;
        }
    }
}
