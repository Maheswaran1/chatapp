package com.zoho.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Email extends ObjectFactory{
	private String emailKey;
	private  String userId;
	private String emailId;
	private long createdTime;
	private boolean primaryEmail;
	private boolean verifed;
	private long modifiedTime;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;                                                                    
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public boolean isPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(boolean primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public boolean isVerifed() {
		return verifed;
	}
	public void setVerifed(boolean verifed) {
		this.verifed = verifed;
	}
	public long getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(long modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	
	
	public Email(HashMap<String, Object> map) {
		super(map);
		setTableName("Email");
		setColumnList(getEmailColumnList());
		init();
	}
	
	private ArrayList<String> getEmailColumnList() {
		ArrayList<String> Column = new ArrayList<String>();
		Column.add("Object");
		Column.add("AllEmail");
		return Column;
	}
		
private void init() {
	if(map.get("UserId") != null) {
	this.userId = (String) map.get("UserId");
	}
	
	if(map.get("EmailId") != null) {
	this.emailId = (String) map.get("EmailId");
	}
	
	if(map.get("CreatedTime") != null) {
		if(map.get("CreatedTime") instanceof String) {
			this.createdTime = Long.parseLong((String) map.get("CreatedTime"));
		}else {
			this.createdTime =(long)map.get("CreatedTime");
		}
	}
	
	if(map.get("PrimaryEmail") != null) {
		if(map.get("PrimaryEmail") instanceof String) {
			this.primaryEmail = Boolean.parseBoolean((String) map.get("PrimaryEmail"));
		}else {
			this.primaryEmail =(boolean)map.get("PrimaryEmail");
		}
	}
	
	if(map.get("Verified") != null) {
		if(map.get("Verified") instanceof String) {
			this.verifed = Boolean.parseBoolean((String)map.get("Verified"));
		}else {
		this.verifed = (boolean)map.get("Verified");
		}
	}
	
	if(map.get("ModifiedTime") != null) {
		if(map.get("ModifiedTime") instanceof String) {
			this.modifiedTime = Long.parseLong((String) map.get("ModifiedTime"));
		}else {
			this.modifiedTime =(long)map.get("ModifiedTime");
		}
	}
	if(map.get("Emailkey") != null) {
		this.emailKey =(String)map.get("Emailkey");
		}
		
	}
		
	
	public Email() {
		setColumnList(getEmailColumnList());
	}
	public String getEmailKey() {
		return emailKey;
	}
	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}

	
	
	
	
	

}
