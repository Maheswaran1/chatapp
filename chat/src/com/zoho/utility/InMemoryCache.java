package com.zoho.utility;

import java.util.HashMap;
import java.util.LinkedList;

import com.zoho.model.SessionManager;
import com.zoho.model.User;

public class InMemoryCache{

	
private static  InMemoryCache inMemoryCache;
	private final int capcity = 50;
	
	private HashMap<String, SessionManager> cacheMap = new HashMap<>();
	private LinkedList<String> cacheList = new LinkedList<>();
	private HashMap<String,User> userCache = new HashMap<>();
	private LinkedList<String> userList = new LinkedList<>();
	
	public static InMemoryCache getInstance() {
		if(inMemoryCache == null) {
		
			synchronized (InMemoryCache.class) {
			
				if(inMemoryCache == null) {					
					inMemoryCache = new InMemoryCache();
				}
			}
		}
		return inMemoryCache;
	}
	private InMemoryCache() {
		
	}
	public synchronized void put(String key, SessionManager sessionManager) {
		
		if (cacheList.size() >=capcity) {
			String keyRemove = cacheList.removeLast();
			cacheMap.remove(keyRemove);
		}
		cacheList.addFirst(key);
		cacheMap.put(key, sessionManager);
	}
	
	public SessionManager get(String key) {
		SessionManager sessionManager = new SessionManager();
		sessionManager = cacheMap.get(key);
		if (sessionManager != null) {
			cacheList.remove(key);
			cacheList.addFirst(key);
		}
		
		return sessionManager;
		
	}
	
	public synchronized void putUser(String key, User user) {
		
		if (userList.size() >=capcity) {
			String keyRemove = userList.removeLast();
			userCache.remove(keyRemove);
		}
		userList.addFirst(key);
		userCache.put(key, user);
	}
	
	public User getUser(String key) {
		User user = new User();
		user = userCache.get(key);
		if (user != null) {
			userList.remove(key);
			userList.addFirst(key);
		}
		return user;
		
	}
	public void removeSession(String key) {
		cacheMap.remove(key);
	}
	
	public void removeUser(String key) {
		userCache.remove(key);
	}


}
