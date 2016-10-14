package com.broaderator.mcserver.core.modules.bukkit;

import com.broaderator.mcserver.core.$;
import org.bukkit.ChatColor;

import java.util.HashMap;

// refers to in-game messages ONLY
// this should be the only chat formatter
public class Message {
	protected static final HashMap<String, String> ColorCodes = new HashMap<>();

	// ColorCodes shouldn't be edited anywhere else
	static {
		for (ChatColor color : ChatColor.values()) {
			ColorCodes.put(color.name().toLowerCase(), color.toString());
		}
	}

	public static String format(String msg) {
		return $.replace(msg, ColorCodes);
	}
}
