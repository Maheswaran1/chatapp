package com.zoho.RedisDao;


import com.zoho.database.RedisCache;
import com.zoho.model.SessionManager;

import apiInterFace.SessionManagerInterface;

public class SessionRedisDao implements SessionManagerInterface{
	RedisCache cache =RedisCache.getInstance();

	@Override
	public String getUserId(String sessionId) {
		String tableName = "SessionManager";
		SessionManager session = null;
		try {
			session = (SessionManager) cache.getFromJedis(sessionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session.getUserId();
	}

	@Override
	public SessionManager getSessionObject(String sessionId) {
		SessionManager session = null;
		try {
			session = (SessionManager) cache.getFromJedis(sessionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
	}
		
}