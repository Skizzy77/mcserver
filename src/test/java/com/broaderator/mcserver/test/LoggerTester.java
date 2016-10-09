package com.broaderator.mcserver.test;

import com.broaderator.mcserver.core.logger.Logger;

public class LoggerTester implements TestMain.Test {
	@Override
	public void test() {
		Logger.self.start();
		for (Logger.Level level : Logger.Level.values()) {
			Logger.log("Test", level, "this is a " + level.name() + " log");
		}
		Logger.self.stop();
	}
}
