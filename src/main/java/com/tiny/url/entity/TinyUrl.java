package com.tiny.url.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TINYURL")
public class TinyUrl {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="FULL_URL")
	private String fullUrl;
	
	@Column(name="TINY_URL")
	private String tinyUrl;
	
	@Column(name="AUTHENTICATION_KEY")
	private String authenticationKey;
	
	@Column(name="SUB_DOMAIN")
	private String subDomain;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getAuthenticationKey() {
		return authenticationKey;
	}

	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}

	public String getSubDomain() {
		return subDomain;
	}

	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}

}
