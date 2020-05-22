package com.tiny.url.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tiny.url.dao.TinyUrlDao;
import com.tiny.url.entity.TinyUrl;

@Repository
@Transactional
public class TinyUrlDaoImpl implements TinyUrlDao {

	@Autowired
	SessionFactory sessionFactory;
	
	// Returns TinyUrl Entity based on the tiny url and authentication key provided
	@Override
	public TinyUrl getFullUrl(String tinyUrl, String key) {
		
		TinyUrl url = null;
		List<TinyUrl> list = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("From TinyUrl where tinyUrl = :link and authenticationKey = :key");
			query.setParameter("link", tinyUrl);
			query.setParameter("key", key);
			
			list = (List<TinyUrl>) query.list();
			if(!list.isEmpty()) {
				url = list.get(0);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	// saves the TinyUrl entity in database TINYURL table
	@Override
	public String saveTinyUrl(TinyUrl urlEntity) {
		String status = "failed";
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(urlEntity);
			status = "success";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
