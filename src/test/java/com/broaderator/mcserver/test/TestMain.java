package com.broaderator.mcserver.test;

import java.util.Arrays;
import java.util.List;

public class TestMain {

	public static List<Test> testQueue = Arrays.asList(
		new MessageBuilderTester(),
		// new LoggerTester(),
		new GameMessageTester()
	);

	public static void main(String[] args) {
		for(Test test : testQueue) {
			System.out.println("TestMain >> Now testing " + test.getClass().getSimpleName());
			test.test();
		}
		System.out.println("TestMain >> Testing done");
	}

	// All tests inherit this interface
	protected interface Test {
		void test();
	}
}
