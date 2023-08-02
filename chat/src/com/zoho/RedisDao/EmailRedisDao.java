package com.zoho.RedisDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zoho.database.RedisCache;
import com.zoho.model.Email;
import com.zoho.model.ObjectFactory;

import apiInterFace.EmailApiInterface;

public class EmailRedisDao implements EmailApiInterface {
	RedisCache cache = RedisCache.getInstance();

	@Override
	public ArrayList<Email> getAllEmail(String userId) {
		ArrayList<Email> emailList = new ArrayList<Email>();
		ArrayList<Email> verifiedList = getVerifedEmails(userId,true);
		ArrayList<Email> nonverifedList =getVerifedEmails(userId,false);
		if(nonverifedList.isEmpty()&&verifiedList.isEmpty()) {
			return null;
		}
		emailList.addAll(nonverifedList);
		emailList.addAll(verifiedList);
		
		return emailList;
	}

	@Override
	public Email getPrimaryEmail(String userId) {
		Email email = null;
		try {
			email = (Email) cache.getFromJedis("PrimaryEmail"+userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}

	@Override
	public String getUserId(String EmailId) {
		Email userId = null;
		try {
			userId = (Email) cache.getFromJedis(EmailId+"UserId");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId.getUserId();
	}

	@Override
	public ArrayList<Email> getVerifedEmails(String userId,boolean status) {
		 List<String> emailIdList = cache.getListObject("Email"+userId+status);
		 ArrayList<Email> emailObjectList = new ArrayList<>();
		 for(String emailId : emailIdList) {
			 Email email = getEmailObject(emailId);
			 if(email != null) {
			 emailObjectList.add(email);
			 }
		 }
		return emailObjectList;
	}

	@Override
	public Email getEmailObject(String emailId) {
		Email email = null;
		try {
			email = (Email) cache.getFromJedis(emailId);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}

}
