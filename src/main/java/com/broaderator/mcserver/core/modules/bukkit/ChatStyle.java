package com.broaderator.mcserver.core.modules.bukkit;

import com.broaderator.mcserver.core.$;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashSet;

public class ChatStyle {

	static {
		$.ns.put("Chat.Styles", ImmutableSet.of(

			// Mineplex-style
			new ChatStyle(
				"${rank}${reset} ${yellow}${displayName}${reset} ${gray}${msg}",
				"${prefixColor}${prefix}>${reset} ${msgColor}${msg}",
				"Mineplex"
			),

			// Hypixel-style
			new ChatStyle(
				"[${rank}]${reset} ${dark_green}${displayName}${reset}: ${gray}${msg}",
				"${prefixColor}${prefix}:${reset} ${msgColor}${msg}",
				"Hypixel"
			)

		));
	}

	public String chatFormat;
	public String commandFormat;

	// static ends and object begins
	// a struct equivalent
	public String name;

	public ChatStyle(
		String chatFormat,
		String commandFormat,
		String name) {
		this.chatFormat = chatFormat;
		this.commandFormat = commandFormat;
		this.name = name;
	}

	public static ChatStyle getCsByName(String name) {
		for (ChatStyle cs : (ImmutableSet<ChatStyle>) $.ns.get("Chat.Styles"))
			if (cs.name.equals(name))
				return cs;
		return null;
	}

	public static Collection<String> getCsNames() {
		Collection<String> output = new HashSet<>();
		((ImmutableSet<ChatStyle>) $.ns.get("Chat.Styles")).forEach((style) -> output.add(style.name));
		return output;
	}
}
