package com.broaderator.mcserver.core.modules.bukkit;

import org.bukkit.Location;

/*
Common tools for Bukkit (block, player, listener, commands) manipulation
 */
public class BukkitTools {

	// returns whether t is in range of a to b (non-inclusive)
	private static boolean intIsBetween(int a, int b, int t) {
		return Math.abs(t - a) < Math.abs(a - b) && Math.abs(t - b) < Math.abs(a - b);
	}

	public static boolean isInRange(Location corner0, Location corner1, Location test) {
		if (corner0.getWorld() != corner1.getWorld())
			return false;

		return
			test.getWorld() == corner0.getWorld() &&
				intIsBetween(corner0.getBlockX(), corner1.getBlockX(), test.getBlockX()) &&
				intIsBetween(corner0.getBlockY(), corner1.getBlockY(), test.getBlockY()) &&
				intIsBetween(corner0.getBlockZ(), corner1.getBlockZ(), test.getBlockZ());
	}
}
