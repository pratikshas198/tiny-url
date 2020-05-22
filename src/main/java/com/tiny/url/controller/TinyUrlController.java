package com.tiny.url.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/getTinyUrl", method = {RequestMethod.POST}, produces = {"text/xml"})
	public @ResponseBody String getTinyUrl() {
		String tinyUrlXml = null;
		String fullUrl;
		String subDomain;
		String key;
		
		fullUrl = request.getParameter("fullUrl");
		subDomain = request.getParameter("subDomain");
		key = request.getParameter("authenticationkey");
		
		tinyUrlXml = tinyUrlService.generateTinyUrl(fullUrl, subDomain, key);
		return tinyUrlXml;
	}
	
	@RequestMapping(value="/getFullUrl", method = {RequestMethod.POST}, produces = {"text/xml"})
	public @ResponseBody String getFullUrl(){
		String fullUrlXml = null;
		String tinyUrl;
		String key;
		
		tinyUrl = request.getParameter("tinyUrl");
		key = request.getParameter("authenticationKey");
		
		fullUrlXml = tinyUrlService.getFullUrl(tinyUrl, key);
		return fullUrlXml;
	}
	
}
