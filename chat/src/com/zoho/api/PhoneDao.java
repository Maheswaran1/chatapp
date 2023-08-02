package com.zoho.api;

import java.util.HashMap;

import com.zoho.database.ExcuteQuery;
import com.zoho.model.Phone;
import com.zoho.utility.Utility;

public class PhoneDao{
				
			static final String tableName = "Phone";
			

			public  String getTableName() {
				return tableName;
			}


			public Phone getObject(HashMap<String, Object> phoneMap) {
				Utility utility = new Utility();
				IdGeneratorApi idGenerator =IdGeneratorApi.getInstance(); 
				phoneMap.put("PhoneId", idGenerator.getPhoneId());
				phoneMap.put("CreatedTime", utility.getCurrentTime());
				phoneMap.put("ModifiedTime",utility.getCurrentTime());
				Phone phone = new Phone(phoneMap);
				return phone;
			}


			public String checkPhoneNumber(HashMap<String, Object> phoneCheckMap) throws Exception {
				String[] column = {"UserId"};
				Phone phone = select(column,phoneCheckMap);
				String userId = phone.getUserId();
			
				return userId;
			}


			private Phone select(String[] column, HashMap<String, Object> phoneCheckMap) {
				ExcuteQuery obj = new ExcuteQuery();
				Phone phone = new Phone();
				try {
					phone = (Phone) obj.select(tableName,column, phoneCheckMap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return phone;

			
			}


			public Phone select(HashMap<String, Object> phoneCheckMap) throws Exception {
				String [] column = {"*"};
				Phone phone = select(column,phoneCheckMap);
				return phone;
			}


}
