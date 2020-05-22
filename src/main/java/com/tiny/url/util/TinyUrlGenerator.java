package com.tiny.url.util;

public class TinyUrlGenerator {

	public static final int RADIX = 36;
	public static final String PIPE = "-";
	
	public static String generateTinyUrlHash(String fullUrl) {
		
		String tinyUrlHash = null;
		
		if(fullUrl != null && !fullUrl.equals("")) {
			tinyUrlHash = Integer.toString(fullUrl.hashCode(),RADIX);
			if(tinyUrlHash.startsWith(PIPE)) {
				tinyUrlHash = tinyUrlHash.substring(1);
			}
		}
		
		return tinyUrlHash;
	}
	
	public static String containedProtocol(String url) {
		String protocol = "";
		
		String[] splitStr = url.split("://");
		if(splitStr.length > 0 && ("http".equalsIgnoreCase(splitStr[0]) || "https".equalsIgnoreCase(splitStr[0]))) {
			protocol = splitStr[0];
		}
		return protocol;
	}
}
