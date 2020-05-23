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
	public String generateTinyUrl(String fullUrl, String subDomain) {
		String tinyUrl = null;
		TinyUrl entity;
		try {
			
			if(fullUrl != null && !fullUrl.trim().equals("") && subDomain != null && !subDomain.trim().equals("")){
				
				//to check if original url with required subdomain already present in database
				entity = tinyUrlDao.getTinyUrlEntityByFullUrlAndSubDomain(fullUrl.trim(), subDomain.trim());
				if(entity != null) {
					 tinyUrl = entity.getTinyUrl();
				}else {
					entity = new TinyUrl();
					entity.setFullUrl(fullUrl.trim());
					entity.setSubDomain(subDomain.trim());
					
					//save full url in database to get unique auto generated id 
					String status = tinyUrlDao.saveTinyUrl(entity);
					if("success".equals(status)) {
						entity = tinyUrlDao.getTinyUrlEntityByFullUrlAndSubDomain(fullUrl.trim(), subDomain.trim());
						
						//generate hash string from unique id
						String hash = TinyUrlGenerator.generateTinyUrlHash(entity.getId());
						
						//get protocol from original url
						String protocol = TinyUrlGenerator.containedProtocol(fullUrl);
						
						if(!protocol.equals("")) {
							
							tinyUrl = protocol + "://" + subDomain + "/" + hash;
							entity.setTinyUrl(tinyUrl);
							
							// update entity stored in database with generated tiny url 
							String updateStatus = tinyUrlDao.updateTinyUrl(entity);
							
							if (!"success".equals(updateStatus)) { 
								tinyUrl = null;
							} 
						}
						
					}
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tinyUrl;
	}

	@Override
	public String saveTinyUrl(TinyUrl urlEntity) {
		String status = tinyUrlDao.saveTinyUrl(urlEntity);
		return status;
	}

	@Override
	public String getFullUrl(String tinyUrl) {
		String fullUrl = null;
		TinyUrl entity;
		try {
			
			if(tinyUrl != null && !tinyUrl.trim().equals("")){
					
				entity = tinyUrlDao.getTinyUrlEntityByTinyUrl(tinyUrl.trim());	
				if(entity != null)	{
					fullUrl = entity.getFullUrl();
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fullUrl;
	}

}
