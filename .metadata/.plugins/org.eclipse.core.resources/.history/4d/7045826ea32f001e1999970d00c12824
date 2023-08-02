package com.zoho.model;

import java.util.ArrayList;
import java.util.HashMap;

public class SessionManager extends ObjectFactory{
	
	private String userId;
	private String sessionId;
	private long validityTime;
	
	public SessionManager(HashMap<String,Object> map) {
		super(map);
		setTableName("SessionManager");
		setColumnList(getSessionColumnList());
		init();
		
	}
	
	private ArrayList<String> getSessionColumnList() {
		ArrayList<String> Column = new ArrayList<String>();
		Column.add("Object");
		return Column;
	}
	
	
	public SessionManager() {
		setColumnList(getSessionColumnList());
	}


	private void init() {
		if(map.get("UserId") != null) {
			this.userId = (String) map.get("UserId");
		}
		
		if(map.get("SessionId") != null) {
			this.sessionId= (String) map.get("SessionId");
			System.out.println(sessionId +"sessionId in maodl");
		}
		
		if(map.get("ValidityTime") != null) {
			if(map.get("ValidityTime") instanceof String) {
				this.validityTime = Long.parseLong((String) map.get("ValidityTime"));
			}else {
			this.validityTime = (long) map.get("ValidityTime");
			}
		}
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public long getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(long validityTime) {
		this.validityTime = validityTime;
	}
	

}
