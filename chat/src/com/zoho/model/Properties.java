package com.zoho.model;

import java.util.HashMap;

public class Properties extends ObjectFactory{
	
	private static final long serialVersionUID = 4635569862509519112L;
	private String PropertyId;
	private String portNumber;
	private String serverNumber;
	private String PropertyName;
	
	public Properties(HashMap<String,Object> map) {
		
	}
	public String getPropertyId() {
		return PropertyId;
	}
	public void setPropertyId(String propertyId) {
		PropertyId = propertyId;
	}
	
	public String getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public String getServerNumber() {
		return serverNumber;
	}
	public void setServerNumber(String serverNumber) {
		this.serverNumber = serverNumber;
	}
	public String getPropertyName() {
		return PropertyName;
	}
	public void setPropertyName(String propertyName) {
		PropertyName = propertyName;
	}

}
