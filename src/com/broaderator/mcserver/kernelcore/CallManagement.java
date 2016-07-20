package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CallManagement {
    public static List<String> DisabledCalls = new ArrayList<>();

    public static void Call(String name, Object... args) {
        if (((HashMap<String, Object>) $.globalVolNS.get("Calls")).containsKey(name)) {
            if (DisabledCalls.contains(name)) {
                Bukkit.getLogger().info("Attempt to call disabled KernelCall: " + name);
            } else {
                ((Function) $.globalVolNS.get("Calls")).run(args);
            }
        } else {
            Bukkit.getLogger().severe("Attempt to call nonexistent KernelCall: " + name);
        }
    }
}
