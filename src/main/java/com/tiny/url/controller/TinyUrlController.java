package com.tiny.url.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiny.url.entity.json.TinyUrlJson;
import com.tiny.url.service.TinyUrlService;

@Controller
public class TinyUrlController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired(required=true)
	private ApplicationContext context;
	
	@Autowired
	TinyUrlService tinyUrlService;
	
	@RequestMapping(value = "/getFullUrl", method = RequestMethod.POST)
	public @ResponseBody TinyUrlJson getFullUrl(@RequestBody TinyUrlJson url) {
		String fullUrl;
		fullUrl = tinyUrlService.getFullUrl(url.getTinyUrl());
		url.setFullUrl(fullUrl);
		return url;
	}
	
	@RequestMapping(value = "/getTinyUrl", method = RequestMethod.POST)
	public @ResponseBody TinyUrlJson getTinyUrl(@RequestBody TinyUrlJson url) {
		String tinyUrl;
		tinyUrl = tinyUrlService.generateTinyUrl(url.getFullUrl(), url.getSubDomain());
		url.setTinyUrl(tinyUrl);
		return url;
	}
	
}
