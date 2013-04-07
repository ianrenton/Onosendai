package com.vaguehope.onosendai.provider;

public enum NetworkType {
	TWITTER("twitter"),
	FACEBOOK("facebook");

	private final String name;

	private NetworkType (final String name) {
		this.name = name;
	}

	public static NetworkType parse (final String s) {
		for (NetworkType type : values()) {
			if (s.equalsIgnoreCase(type.name)) return type;
		}
		return null;
	}

}