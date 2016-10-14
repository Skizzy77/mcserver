package com.broaderator.mcserver.core;


import org.bukkit.Bukkit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

// shorthand class, inspired by jQuery
public class $ {
	public static final Logger log = Bukkit.getLogger();
	public static final HashMap<String, Object> ns = new HashMap<>();
	public static final Properties savedNS = new Properties();
	protected static final String FAIL = "Core error caught! The plugin will not run as expected. Stop it as fast as you can!";

	static {
		loadProperties(savedNS, GlobalConstants.HomeFolder + "prop.properties");
	}

	// store properties
	public static boolean writeProperties(Properties prop, String path, String comments) {
		try {

			FileOutputStream fos = new FileOutputStream(path);
			prop.store(fos, comments);
			fos.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(FAIL);
			return false;
		}

	}

	// load properties
	public static boolean loadProperties(Properties prop, String path) {
		try {

			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(FAIL);
			return false;
		}
	}

	/**
	 * Replaces notated sections of the input string to a specified value in the map.
	 * For example, <code>replace("He says ${hisWords}", new HashMap<>(){{put("hisWords", "Hello World");}});</code> returns "He says Hello World".
	 *
	 * @param input  The original string to replace.
	 * @param keyVal The key-value sets to follow.
	 * @return Replaced string
	 */
	public static String replace(String input, HashMap<String, String> keyVal) {
		for (Map.Entry<String, String> entry : keyVal.entrySet()) {
			input = input.replace("${" + entry.getKey() + "}", entry.getValue());
		}
		return input;
	}
}