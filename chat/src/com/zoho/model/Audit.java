package com.zoho.model;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class Audit extends ObjectFactory{

	private String auditId;
	private String userId;
	private String table;
	private JSONObject oldValues;
	private JSONObject newValues;
	private long time;
	
	
	public Audit(HashMap<String, Object> auditMap) {
		super(auditMap);
		setTableName("Audit");
		init();
		
	}
	private void init() {
		if(map.get("UserId") != null) {
			this.userId = (String) map.get("UserId");
		}
		
		if(map.get("TableName") != null) {
			this.table = (String) map.get("TableName");
		}
		
		if(map.get("OldValues") != null) {
			this.oldValues =  (JSONObject) map.get("OldValues");
		}
		
		if(map.get("NewValues") != null) {
			this.newValues = (JSONObject) map.get("NwValues");
		}
		
		if(map.get("Time") != null) {
			this.time = (long) map.get("Time");
		}
		if(map.get("AuditId") != null) {
			this.auditId =(String)map.get("AuditId");
			}
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public JSONObject getOldValues() {
		return oldValues;
	}
	public void setOldValues(JSONObject oldValues) {
		this.oldValues = oldValues;
	}
	public JSONObject getNewValues() {
		return newValues;
	}
	public void setNewValues(JSONObject newValues) {
		this.newValues = newValues;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	
	public Audit() {
		
	}
}
