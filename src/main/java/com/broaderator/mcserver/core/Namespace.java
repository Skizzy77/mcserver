package com.broaderator.mcserver.core;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;
import java.util.HashMap;

// Where most of the public resources used by other features are stored. This allows easier management and debugging.
// Also used for players
public class Namespace implements Serializable {
	private HashMap<String, String> data;
	private final String name;
	private HashMap<String, String> metadata = new HashMap<>();

	// Some tools to be used below
	private static Gson gson = new Gson();

	public Namespace(String name) {
		this.name = name;
	}

	// Data manipulation proxy
	public void setString(String key, String value) {
		this.data.put(key, gson.toJson(value));
	}

	@Override
	public String toString() {
		return String.format("<CoreNamespace:%s#%d>", this.name, this.hashCode());
	}
}
