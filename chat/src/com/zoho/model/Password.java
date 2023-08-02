package com.zoho.model;

import java.util.HashMap;

public class Password extends ObjectFactory{
		private String passwordId;
		private String userId;
		private String password;
		private long nextModifyDate;
		private String salt;
		private int algorithamUsed;
		
		
		public String getUserId() {
			return userId;
		}
		public void setUser_Id(String userId) {
			this.userId = userId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public long getNextModifyDate() {
			return nextModifyDate;
		}
		public void setNextModifyDate(long nextModifyDate) {
			this.nextModifyDate = nextModifyDate;
		}
		public Password(String user_Id, String password) {
			super();
			this.userId = user_Id;
			this.password = password;
		}
		public Password(HashMap<String, Object> map) {
			super(map);
			setTableName("Password");
			init();
		}
		public Password() {
			// TODO Auto-generated constructor stub
		}
		public void init() {
			if(map.get("UserID") != null) {
				this.userId = (String)map.get("UserId");
			}
			
			if(map.get("Password") != null) {
				this.password = (String) map.get("Password");
			}
			if(map.get("nextModifingDate") != null){
				if(map.get("nextModifingDate") instanceof String) {
					this.nextModifyDate = Integer.parseInt((String) map.get("nextModifingDate"));
				}else {
	
				this.nextModifyDate = (long)map.get("nextModifiedDate");
			}
			}
			
			if(map.get("Salt") != null) {
				this.salt = (String) map.get("Salt");
			}
			if(map.get("AlgorithamUsed") != null) {
				if(map.get("AlgorithamUsed") instanceof String) {
					this.algorithamUsed = Integer.parseInt((String) map.get("AlgorithamUsed"));
				}else {
			}
				this.algorithamUsed = (int) map.get("AlgorithamUsed");
			}
			
			if(map.get("PasswordId") != null) {
				this.setPasswordId((String)map.get("PasswordId"));
				}
			
		}
		public String getSalt() {
			return salt;
		}
		public void setSalt(String salt) {
			this.salt = salt;
		}
		public int getAlgorithamUsed() {
			return algorithamUsed;
		}
		public void setAlgorithamUsed(int algorithamUsed) {
			this.algorithamUsed = algorithamUsed;
		}
		public String getPasswordId() {
			return passwordId;
		}
		public void setPasswordId(String passwordId) {
			this.passwordId = passwordId;
		}
			
		
		
		
}
