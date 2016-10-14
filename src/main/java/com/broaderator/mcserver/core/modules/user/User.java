package com.broaderator.mcserver.core.modules.user;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

// only abstraction of a Bukkit player
public class User {
	// user data is saved at GlobalConstants.HomeFolder/userdata/{uuid}.properties

	public final OfflinePlayer player;
	public final HashMap<String, String> attrs = new HashMap<>();
	protected Properties properties = new Properties();

	protected User(OfflinePlayer player) {
		this.player = player;
	}

	// proxy Properties functions

	public String getProperty(String prop) {
		return properties.getProperty(prop);
	}

	public String getProperty(String prop, String default_) {
		return properties.getProperty(prop, default_);
	}

	public void setProperty(String prop, String value) {
		properties.setProperty(prop, value);
	}

	public void removeProperty(String prop) {
		properties.remove(prop);
	}

	public UUID uuid() {
		return this.player.getUniqueId();
	}

	public String name() {
		return this.player.getName();
	}

	public boolean online() {
		return this.player.isOnline();
	}

	public Player getOnline() {
		if (this.online())
			return this.player.getPlayer();
		return null;
	}
}
