package com.broaderator.mcserver.test;


import com.broaderator.mcserver.core.logger.Format;
import com.broaderator.mcserver.core.logger.MessageBuilder;
import com.google.gson.Gson;

public class MessageBuilderTester implements TestMain.Test {
	public void test() {
		System.out.println(new MessageBuilder()
			.in(Format.Cyan)
				.add("this is cyan bold ", Format.Bold)
				.add("this is cyan underline ", Format.Underline)
			.out()
			.in(Format.Blue)
				.in(Format.Inverse)
					.add("this is blue inverse ")
				.out()
				.add("this is blue bold ", Format.Bold)
			.out().toString());
	}
}
