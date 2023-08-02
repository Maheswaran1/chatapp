package com.zoho.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.zoho.database.ExcuteQuery;
import com.zoho.model.ObjectFactory;


public class IdGeneratorApi {
	
	AtomicInteger userId = new AtomicInteger();
	AtomicInteger groupId = new AtomicInteger();
	AtomicInteger messageId = new AtomicInteger();
	AtomicInteger emailKey = new AtomicInteger();
	AtomicInteger phoneId = new AtomicInteger();
	AtomicInteger passwordId = new AtomicInteger();
	AtomicInteger auditId = new AtomicInteger();
	static AtomicInteger redisPresent = new AtomicInteger();
	
	private static  IdGeneratorApi idGeneretor;
	
	private IdGeneratorApi() {
		
		}
		public static IdGeneratorApi getInstance() {
		if(idGeneretor == null) {
		
			synchronized (IdGeneratorApi.class) {
			
				if(idGeneretor == null) {					
					
					idGeneretor = new IdGeneratorApi();
				}
			}
		
		}
		return idGeneretor;
	}
	public void selectValues() throws Exception {
		HashMap<Object,Object> idValue = getValues();
		String userId = (String) idValue.get("UserId");
		String groupId = (String) idValue.get("GroupId");
		this.userId.set(Integer.parseInt(userId));
		
		String messageId = (String) idValue.get("MessageId");
		this.messageId.set(Integer.parseInt(messageId));
		
		String auditId = (String) idValue.get("AuditId");
		this.auditId.set(Integer.parseInt(auditId));
		
		String emailKey = (String) idValue.get("EmailKey");
		this.emailKey.set(Integer.parseInt(emailKey));
		
		String phoneId = (String) idValue.get("PhoneId");
		this.phoneId.set(Integer.parseInt(phoneId));
		
		String passwordId = (String) idValue.get("PasswordId");
		this.passwordId.set(Integer.parseInt(passwordId));
		
		String isPresent = (String) idValue.get("RedisPresent");
		this.redisPresent.set(Integer.parseInt(isPresent));
		
		this.groupId.set(Integer.parseInt(groupId));
		System.out.println(userId+" "+groupId+"  "+messageId+emailKey);
	}
	
	public void updateValues() throws Exception {
		updateValues("UserId",userId.intValue());
		updateValues("GroupId",groupId.intValue());
		updateValues("MessageId",messageId.intValue());
		updateValues("EmailKey",emailKey.intValue());
		updateValues("PasswordId",passwordId.intValue());
		updateValues("PhoneId",phoneId.intValue());
		updateValues("AuditId",auditId.intValue());
		
	} 
	
	public HashMap<Object,Object> getValues() throws Exception {
		String tableName = "IdGenerator";
		String[] column = {"*"};
		HashMap<String, Object> condition = null;
		HashMap<Object, Object>  idName = new HashMap<>();

		ExcuteQuery obj = new ExcuteQuery();
		ArrayList<ObjectFactory> idList = obj.selectMultiple(tableName, column, condition);
		for(ObjectFactory object : idList) {
			HashMap<String,Object> objectMap = object.getMap();
			
			idName.put(objectMap.get("IdName"), objectMap.get("IdValue"));
			}
		
		return idName;
		
	}
	
	public int updateValues(String idName ,int value) throws Exception {
		String idvalues =String.valueOf(value);
		HashMap<String,Object> condition = new HashMap<String, Object>();
		condition.put("IdName", idName);
		HashMap<String,Object> updateValues = new HashMap<String,Object>();
		updateValues.put("IdValue",idvalues);
		ExcuteQuery object = new ExcuteQuery();
		int status = 0;
		try {
			 status = object.update("IdGenerator",updateValues , condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	}

	public String  getUserId() {
		int id = userId.incrementAndGet();
		String userIdString = String.valueOf(id);
		return userIdString;
	}

	public int getGroupId() {
		return groupId.incrementAndGet();
	}

	public int getMessageId() {
		return messageId.incrementAndGet();
	}

	public  String  getPassworId() {
		int id = passwordId.incrementAndGet();
		String userIdString = String.valueOf(id);
		return userIdString;
	}		
	public  String  getPhoneId() {
		int id = phoneId.incrementAndGet();
		String userIdString = String.valueOf(id);
		return userIdString;
	}
	public  String  getEmailKey() {
		int id = emailKey.incrementAndGet();
		String userIdString = String.valueOf(id);
		return userIdString;
	}

	public String getAuditId() {
		int id = auditId.incrementAndGet();
		String userIdString = String.valueOf(id);
		return userIdString;
	}

	public static int getRedisPrsent() {
		return redisPresent.intValue();
		
	}
	
}