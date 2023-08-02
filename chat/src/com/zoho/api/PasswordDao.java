package com.zoho.api;

import java.sql.SQLException;
import java.util.HashMap;


import com.zoho.database.ExcuteQuery;
import com.zoho.model.Password;
import com.zoho.utility.PasswordHashingClass;
import com.zoho.utility.Utility;

public class PasswordDao{
	
		static final String tableName = "Password";
		static String[] column = {"Password","Salt","AlgorithamUsed"};
		
		
	public  String getTableName() {
			return tableName;
		}

	public Password select(HashMap<String, Object> condition) throws Exception {	
			Password password = new Password();
			
			ExcuteQuery object= new ExcuteQuery();
			password = (Password) object.select(tableName, column, condition);
			return password;
		}
	
	public int insert(HashMap<String,Object> passwordMap) throws Exception {
		Password password = new Password(passwordMap);
		ExcuteQuery object = new ExcuteQuery();
		int status = object.insert(password);
		return status;
	}

	public int update(HashMap<String,Object> updateValues, HashMap<String, Object> condition) throws Exception {
		ExcuteQuery object = new ExcuteQuery();
		int status = object.update(tableName,updateValues,condition);
		return status;	
		}

	public int delete(HashMap<String,Object> passwordMap) throws SQLException {
		return 0;
	}

	public Password getPasswordObject(HashMap<String, Object> condition) throws Exception {
	
		Password password = select(condition);	
		return password;
	}
	
	public Password getObject(HashMap<String, Object> passwordMap,String password) throws Exception {
		Utility utility = new Utility();
		IdGeneratorApi idGenerator = IdGeneratorApi.getInstance();
		String salt = PasswordHashingClass.getNewSalt();
		String encryptedPassword = PasswordHashingClass.getEncryptedPassword(password,salt);
		passwordMap.put("PasswordId", idGenerator.getPassworId());
		passwordMap.put("Password",encryptedPassword) ;
		passwordMap.put("NextModifyDate", utility.nextPasswordChageDate());
		passwordMap.put("Salt",salt);
		passwordMap.put("AlgorithamUsed",2);
		Password passwordObject = new Password(passwordMap);
		return passwordObject;
	}	

	  
	  
	public  boolean authanticateUser(String password,HashMap<String, Object> condition) throws Exception {
			Password passwordObj = getPasswordObject(condition);
			if(passwordObj.getAlgorithamUsed() == 2) {
				String calculatedHash = PasswordHashingClass.getEncryptedPassword(password, passwordObj.getSalt());
				 if(calculatedHash.equals(passwordObj.getPassword())) {
					 String salt = PasswordHashingClass.getNewSalt();
					 String newPassword = PasswordHashingClass.getNewEncryptedPassword(password, salt);
					 updatePassword(newPassword, salt,condition);
					 return true; 
				}
			}else {
			String CalculatedHash = PasswordHashingClass.getNewEncryptedPassword(password, passwordObj.getSalt());
		   if(CalculatedHash.equals(passwordObj.getPassword())){
			   return true;
		   }else {
			   return false;
		   }
		}
			return false;
		}

	private void updatePassword(String password, String salt, HashMap<String, Object> condition) throws Exception {
		HashMap<String,Object> updateValues = new HashMap<String,Object>();
		updateValues.put("Password",password);
		updateValues.put("Salt",salt);
		updateValues.put("AlgorithamUsed",1);
		update(updateValues,condition);
	}
}

