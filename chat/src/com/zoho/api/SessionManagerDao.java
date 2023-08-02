package com.zoho.api;

import java.sql.SQLException;
import java.util.HashMap;

import com.zoho.database.ExcuteQuery;
import com.zoho.database.RedisCache;
import com.zoho.model.SessionManager;
import com.zoho.utility.InMemoryCache;
import com.zoho.utility.Utility;

import apiInterFace.SessionManagerInterface;

public class SessionManagerDao implements SessionManagerInterface {
	
		private static final String tableName = "SessionManager";
	
	public String getSessionId(int userId ) throws Exception {
	
		ExcuteQuery object = new ExcuteQuery();
		String[] column = {"SessionId"};
		HashMap<String,Object> condition = new HashMap<String,Object>();
		condition.put("UserId",userId);
		
		SessionManager sessionManager = (SessionManager) object.select(tableName, column, condition);
		String sessionId = sessionManager.getSessionId();
		return sessionId;
	}
	
	public SessionManager getObject(HashMap<String, Object> sessionMap){
		Utility utility = new Utility();
		sessionMap.put("ValidityTime", utility.getCurrentTime()+(1000*60*60*6));
		sessionMap.put("","");
		SessionManager sessionManger = new SessionManager(sessionMap);
		return sessionManger;
	}
	
	public SessionManager getSessionObject(String sessionId) {
		String[] column = {"*"};
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("SessionId", sessionId);
		ExcuteQuery object = new ExcuteQuery();
		SessionManager sessionManager = null;
		
		try {
			sessionManager = (SessionManager) object.select(tableName, column, condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionManager;
	}
	
	public void InsertSession(HashMap<String,Object> sessionMap) {
		Utility utility = new Utility();
		sessionMap.put("ValidityTime", utility.getCurrentTime()+(1000*60*60*6));
		SessionManager session = new SessionManager(sessionMap);
		
		ExcuteQuery sessionObject = new ExcuteQuery();
		try {
			InMemoryCache inMemoryCache = InMemoryCache.getInstance();
			inMemoryCache.put(session.getSessionId(), session);
			sessionObject.insert(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void removeSession(String sessionId) {
		HashMap<String,Object> condition = new HashMap<String, Object>();
			condition.put("SessionId", sessionId);
			ExcuteQuery object = new ExcuteQuery();
			try {
				object.delete(tableName, condition);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InMemoryCache inMemoryCache =  InMemoryCache.getInstance();
			inMemoryCache.removeSession(sessionId);
			RedisCache redis = RedisCache.getInstance();
		//	redis.removeFromCache("",(String) condition.get("SessionId"));
			
	}

	@Override
	public String getUserId(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
}
