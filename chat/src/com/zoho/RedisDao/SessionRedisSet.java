package com.zoho.RedisDao;

import java.io.IOException;

import com.zoho.api.SessionManagerDao;
import com.zoho.database.RedisCache;
import com.zoho.model.ObjectFactory;
import com.zoho.model.SessionManager;

import apiInterFace.SessionInterface;

public class SessionRedisSet implements SessionInterface{
	
	RedisCache cache = RedisCache.getInstance();
	@Override
	public String getUserId(String sessionId) {
		SessionManagerDao sessionDao = new SessionManagerDao();
		String userId = sessionDao.getUserId(sessionId);
		cache.setDataInRedis(sessionId,userId);
		return userId;
	}

	@Override
	public SessionManager getSessionObject(String sessionId) {
		SessionManagerDao sessionDao = new SessionManagerDao();
		SessionManager session = sessionDao.getSessionObject(sessionId);
		try {
			cache.setObjectInRedis(sessionId,session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
	}

}
