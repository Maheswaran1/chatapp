package com.zoho.model;

import java.util.HashMap;

public class IdGenerator extends ObjectFactory{
	private String idName;
	private int idvalue;
	
	
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public int getIdvalue() {
		return idvalue;
	}
	public void setIdvalue(int idvalue) {
		this.idvalue = idvalue;
	}
	
	public IdGenerator(HashMap<String, Object> map) {
		super(map);
		setTableName("IdGenerator");
		init();
	}
	public IdGenerator() {
		// TODO Auto-generated constructor stub
	}
	private void init() {
		if(map.get("idName") != null) {
			this.idName= (String) map.get("idName");
		}
		
		if(map.get("idValue") != null) {
			this.idvalue= (int) map.get("idValue");
		}
	}
	
}

