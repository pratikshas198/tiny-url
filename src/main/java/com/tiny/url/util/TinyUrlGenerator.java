package com.tiny.url.util;

public class TinyUrlGenerator {

	public static final int RADIX = 36;
	public static final String PIPE = "-";
	
	public static String generateTinyUrlHash(int n) {
		
		String tinyUrlHash = null;
		
		//available 62 characters 
		char[] map = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
				'U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
		
		StringBuffer shortUrl = new StringBuffer();
		while(n>0) {
			shortUrl.append(map[n%62]);
			n=n/62;
		}
		
		//if hash is less than 6 characters add extra '0' character
		int len = shortUrl.length();
		if(len < 6) {
			for(int i=6; i > len ; i--) {
				shortUrl.append('0');
			}
		}
		
		tinyUrlHash = shortUrl.reverse().toString();
		
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
