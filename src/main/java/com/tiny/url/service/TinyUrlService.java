package com.tiny.url.service;

import com.tiny.url.entity.TinyUrl;

public interface TinyUrlService {

	public String generateTinyUrl(String fullUrl, String subDomain, String key);
	
	public String saveTinyUrl(TinyUrl urlEntity);
	
	public String getFullUrl(String tinyUrl, String key);
}
