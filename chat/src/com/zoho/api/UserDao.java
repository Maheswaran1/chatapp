package com.zoho.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.NamingException;
import com.zoho.database.ExcuteQuery;
import com.zoho.model.Email;
import com.zoho.model.ObjectFactory;
import com.zoho.model.User;
import com.zoho.utility.Utility;
import apiInterFace.UserInterface;
public class UserDao implements UserInterface{

	private static final String tableName = "User";
	public  String getTableName() {
		return tableName;
	}
	
	private static final String[] column = {"*"};

	public User select(String [] column ,HashMap<String, Object> condition) throws Exception {
			User user = new User();
			ExcuteQuery obj = new ExcuteQuery();
			user = (User) obj.select(tableName, column, condition);
			user.setEmailList(getAllEmail((String) condition.get("UserId")));
			return user;
	}

	public int insert(HashMap<String, Object> userMap) throws Exception {

		IdGeneratorApi userId = IdGeneratorApi.getInstance();
		userMap.put("UserId", userId.getUserId());

		User user = new User(userMap);
		user.setMap(userMap);

		ExcuteQuery obj = new ExcuteQuery();

		int status = obj.insert(user);

		return status;
	}

	public int delete(HashMap<String,Object> deleteMap) throws SQLException {
		ExcuteQuery queryObject = new ExcuteQuery();
		int status = queryObject.delete(tableName, deleteMap);
		
		return status;
	}

	public User registerUser(HashMap<String, Object> userMap) {
		IdGeneratorApi userId = IdGeneratorApi.getInstance();
		Utility utility = new Utility();
		userMap.put("UserId", userId.getUserId());
		userMap.put("CreatedDate", utility.getCurrentTime());
		userMap.put("ModifiedTime", utility.getCurrentTime());
		User user = new User(userMap);
		return user;

	}

	public int insertUser(ArrayList<ObjectFactory> objectList) throws NamingException, SQLException {
		ExcuteQuery object = new ExcuteQuery();
		int status = object.insertMultiple(objectList);
		return status;
	}

	public ArrayList<Email> getAllEmail(String userId) {
		EmailDao emailApi = new EmailDao();
		ArrayList<Email> emailList = emailApi.getAllEmail(userId);
		return emailList;
	}

	public long updateProfile(String userId,HashMap<String, Object> map) {
		ExcuteQuery queryObject = new ExcuteQuery();
		HashMap<String, Object> condition = new HashMap<String,Object>();
		condition.put("UserId", userId);
		long status= 0;
		try {
			status = queryObject.update(tableName,map,condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	}

	public User select(HashMap<String, Object> condition) {
		User user = null;
		try {
			user = select(column,condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUser(String userId) {
		HashMap<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("UserId", userId);
		User user = select(conditionMap);
		return user;
	}

	@Override
	public int getSpam(String userId) {
		HashMap<String,Object> condition = new HashMap<String, Object>();
		condition.put("UserId", userId);
		String[] column = {"Status"};
		try {
			User user = select(column, condition);
			return user.getSpam();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}



	
}
