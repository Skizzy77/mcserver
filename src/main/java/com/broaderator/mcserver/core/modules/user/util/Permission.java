package com.broaderator.mcserver.core.modules.user.util;

import com.broaderator.mcserver.core.modules.user.User;

public class Permission {

	// all computations are done with the level - the static finals are only for code semantics.

	public static final Permission Guest = new Permission(-10);
	public static final Permission Privileged = new Permission(-5);
	public static final Permission Noble = new Permission(0);
	public static final Permission Developer = new Permission(5);
	public static final Permission Owner = new Permission(30);

	public final short level;

	Permission(int level) {
		this.level = (short) level;
	}

	public static Permission of(User user) {
		return new Permission(Integer.valueOf(user.getProperty("permission", "-10")));
	}

	public boolean isAbove(Permission other) {
		return this.level > other.level;
	}

	public boolean isBelow(Permission other) {
		return this.level < other.level;
	}

	// for example, a command requires permission level 0
	public boolean isEligible(User user) {
		return Short.valueOf(user.getProperty("permission", "-10")) >= this.level;
	}

	public void applyTo(User user) {
		user.setProperty("permission", String.valueOf(this.level));
	}
}
