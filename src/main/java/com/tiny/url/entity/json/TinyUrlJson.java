package com.tiny.url.entity.json;

import java.io.Serializable;

public class TinyUrlJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String fullUrl;
	private String tinyUrl;
	private String subDomain;
	

	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}
	public String getTinyUrl() {
		return tinyUrl;
	}
	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}
	public String getSubDomain() {
		return subDomain;
	}
	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}
	
}
