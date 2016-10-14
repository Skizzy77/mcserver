package com.broaderator.mcserver.test;

import com.broaderator.mcserver.core.modules.bukkit.Message;

public class GameMessageTester implements TestMain.Test {
	@Override
	public void test() {
		System.out.println(
			Message.format(
				"I'm a test; ${blue}this is blue${reset} " +
					"${red}this is red${reset} " +
					"${light_purple}this is pink${reset} " +
					"${bold}this is bold${reset}"));

	}
}
