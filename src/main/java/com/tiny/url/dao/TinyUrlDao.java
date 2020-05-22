package com.tiny.url.dao;

import com.tiny.url.entity.TinyUrl;

public interface TinyUrlDao {

	public TinyUrl getFullUrl(String tinyUrl, String key);
	
	public String saveTinyUrl(TinyUrl urlEntity);
}
