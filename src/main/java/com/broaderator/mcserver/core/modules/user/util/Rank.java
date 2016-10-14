package com.broaderator.mcserver.core.modules.user.util;

import com.broaderator.mcserver.core.modules.bukkit.Message;
import com.broaderator.mcserver.core.modules.user.User;

public class Rank {

	// example: "[ChatColor]Owner"
	public static String getRank(User user) {
		return user.getProperty("rank", "");
	}

	public static void setRank(User user, String newRank) {
		user.setProperty("rank", Message.format(newRank));
	}

}
