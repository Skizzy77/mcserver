package com.broaderator.mcserver.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {

	// All tests inherit this interface
	public interface Test {
		void test();
	}

	public static List<Test> testQueue = Arrays.asList(
		new MessageBuilderTester()
	);

	public static void main(String[] args) {
		for(Test test : testQueue) {
			System.out.println("TestMain >> Now testing " + test.getClass().getSimpleName());
			test.test();
		}
		System.out.println("TestMain >> Testing done");
	}
}
