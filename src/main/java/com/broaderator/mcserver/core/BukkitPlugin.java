package com.broaderator.mcserver.core;

import com.broaderator.mcserver.core.logger.Format;
import com.broaderator.mcserver.core.logger.MessageBuilder;
import com.broaderator.mcserver.core.modules.bukkit.Chat;
import com.broaderator.mcserver.core.modules.user.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BukkitPlugin extends JavaPlugin {
	public static List<? extends Module> Modreg;

	static {

		List<? extends Module> editableModreg = Arrays.asList(
			new UserManager(),
			new Chat()
		);

		Modreg = Collections.unmodifiableList(editableModreg);
	}

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
