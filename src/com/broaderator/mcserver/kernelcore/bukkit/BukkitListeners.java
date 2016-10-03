package com.broaderator.mcserver.kernelcore.bukkit;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore._;
import com.broaderator.mcserver.kernelcore.event.Action;
import com.broaderator.mcserver.kernelcore.moduleBase.ModuleUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BukkitListeners implements Listener, KernelObject {
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent pcpe){
        if ($.globalVolNS.get("KernelInitialized") == false) {
            pcpe.getPlayer().sendMessage("I haven't finished reloading yet! One sec.");
            return;
        }
        Action result = ModuleUtils.getEventByOwner("CommandManager", "Preprocess").call(this, new Action(_.createHashmap(
                "Player", pcpe.getPlayer(),
                "Message", pcpe.getMessage(),
                "Cancelled", pcpe.isCancelled()
        )));
        if (result.proceed) {
            // todo: not done
        }
    }

    @Override
    public String getComponentName() {
        return "BukkitListenerAdapter";
    }
}
