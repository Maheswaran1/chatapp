package com.zoho.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.zoho.database.ExcuteQuery;
import com.zoho.model.Audit;
import com.zoho.model.ObjectFactory;
import com.zoho.utility.Utility;


public class AuditDao {

	public void insert(ArrayList<ObjectFactory> objectList) {

		for (int i = 0; i < objectList.size(); i++) {
			ObjectFactory object = objectList.get(i);
			HashMap<String, Object> auditMap = new HashMap<String, Object>();
			auditMap.put("Action","Insert");
			Audit audit = getObject(object, auditMap);
			ExcuteQuery queryObject = new ExcuteQuery();
			try {
				queryObject.insertAudit(audit);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Audit getAuidtObject(ObjectFactory object, HashMap<String, Object> condition) {
		HashMap<String, Object> auditMap = new HashMap<String, Object>();
		String[] column = (String[]) object.getMap().keySet().toArray(new String[0]);
		

		ExcuteQuery queryObject = new ExcuteQuery();
		ObjectFactory oldObject = null;
		try {
			oldObject = queryObject.select(object.getTableName(), column, condition);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(oldObject.getMap());
		auditMap.put("OldValues", json);
		auditMap.put("Action","update");
		Audit audit = getObject(object, auditMap);
		return audit;
	}

	public Audit getObject(ObjectFactory object, HashMap<String, Object> auditMap) {
		
		if(object != null) {
		JSONObject json = new JSONObject(object.getMap());
		auditMap.put("NewValues", json.toString());
		auditMap.put("TableName", object.getTableName());
		}
		Utility utility = new Utility();

		IdGeneratorApi idGenerator = IdGeneratorApi.getInstance();
		auditMap.put("AuditId", idGenerator.getAuditId());
		auditMap.put("Time", utility.getCurrentTime());
		Audit audit = new Audit(auditMap);

		return audit;

	}

	public Audit deleteinsert(String tableName, HashMap<String, Object> condition) {
		String[] column = {"*"};
		ExcuteQuery queryObject = new ExcuteQuery();
		ObjectFactory object = null;
		try {
			object = queryObject.select(tableName, column, condition);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = new JSONObject(object.getMap());
		HashMap<String, Object> auditMap = new HashMap<String, Object>();
		auditMap.put("Oldvalues", json.toString());
		auditMap.put("TableName", tableName);
		auditMap.put("Action","delete");
		Audit audit = getObject(null,auditMap);
		return audit;		
		
	}

}
