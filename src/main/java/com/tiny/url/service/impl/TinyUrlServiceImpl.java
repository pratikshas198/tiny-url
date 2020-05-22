package com.tiny.url.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.url.dao.TinyUrlDao;
import com.tiny.url.entity.TinyUrl;
import com.tiny.url.service.TinyUrlService;
import com.tiny.url.util.TinyUrlGenerator;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {
	
	@Autowired
	TinyUrlDao tinyUrlDao;

	@Override
	public String generateTinyUrl(String fullUrl, String subDomain, String key) {
		String tinyUrlXml = null;
		String tinyUrl;
		TinyUrl entity;
		try {
			
			if(fullUrl != null && !fullUrl.trim().equals("") && subDomain != null && !subDomain.trim().equals("")){
				
				//code to generate base 36 hashcode from full url
				String hash = TinyUrlGenerator.generateTinyUrlHash(fullUrl.trim());
				
				//code to get protocol from original url
				String protocol = TinyUrlGenerator.containedProtocol(fullUrl);
				
				if(!protocol.equals("") ) {
					//code to create custom tiny url
					tinyUrl = protocol + "://" + subDomain + "/" + hash;
					
					// create an url entity to save in database
					entity = new TinyUrl();
					entity.setAuthenticationKey(key.trim());
					entity.setFullUrl(fullUrl.trim());
					entity.setTinyUrl(tinyUrl.trim());
					entity.setSubDomain(subDomain.trim());
					
					String status = saveTinyUrl(entity);
					
					if ("success".equals(status)) {
						//code to generate xml response
						StringBuffer sb = new StringBuffer();
						sb.append("<?xml version='1.0' encoding='utf-8'?>");
						sb.append("<data><tinyUrl>");
						sb.append("<![CDATA[");
						sb.append(tinyUrl + "]]>");
						sb.append("</tinyUrl></data>");
						
						tinyUrlXml = sb.toString();
					}
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tinyUrlXml;
	}

	@Override
	public String saveTinyUrl(TinyUrl urlEntity) {
		String status = tinyUrlDao.saveTinyUrl(urlEntity);
		return status;
	}

	@Override
	public String getFullUrl(String tinyUrl, String key) {
		String fullUrlXml = null;
		String fullUrl;
		TinyUrl entity;
		try {
			
			if(tinyUrl != null && !tinyUrl.trim().equals("")){
					
				entity = tinyUrlDao.getFullUrl(tinyUrl.trim(), key);	
				if(entity != null)	{
					fullUrl = entity.getFullUrl();
					if (fullUrl != null && !fullUrl.equals("")) {
						//code to generate xml response
						StringBuffer sb = new StringBuffer();
						sb.append("<?xml version='1.0' encoding='utf-8'?>");
						sb.append("<data><fullUrl>");
						sb.append("<![CDATA[");
						sb.append(fullUrl + "]]>");
						sb.append("</fullUrl></data>");
						
						fullUrlXml = sb.toString();
					}
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fullUrlXml;
	}

}
