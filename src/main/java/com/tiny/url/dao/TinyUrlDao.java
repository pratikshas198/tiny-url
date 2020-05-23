package com.tiny.url.dao;

import com.tiny.url.entity.TinyUrl;

public interface TinyUrlDao {

	public TinyUrl getTinyUrlEntityByTinyUrl(String tinyUrl);
	
	public String saveTinyUrl(TinyUrl urlEntity);
	
	public TinyUrl getTinyUrlEntityByFullUrlAndSubDomain(String fullUrl, String subDomain); 
	
	public String updateTinyUrl(TinyUrl urlEntity);
}
