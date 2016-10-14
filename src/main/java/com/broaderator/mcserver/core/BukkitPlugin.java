package com.broaderator.mcserver.core;

import com.broaderator.mcserver.core.logger.Format;
import com.broaderator.mcserver.core.logger.MessageBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitPlugin extends JavaPlugin {

	public void onEnable() {
		$.log.fine(new MessageBuilder().add("Enabling McServer", Format.Cyan).toString());

		// call module.start()
	}

	public void onDisable() {
		$.log.fine(new MessageBuilder().add("Disabling McServer", Format.Cyan).toString());

		// call module.stop()

		/* Output properties */
		$.writeProperties($.savedNS,
			GlobalConstants.HomeFolder + "prop.properties",
			"McServer Saved Namespace");
	}

}
