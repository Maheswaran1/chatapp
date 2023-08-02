package com.zoho.model;

import java.util.HashMap;

public class Message extends ObjectFactory{
	private int messageId;
	private int fromUserId;
	private int toUserId;
	private String status;
	private String content;
	private String deletionStatus;
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDeletionStatus() {
		return deletionStatus;
	}
	public void setDeletionStatus(String deletionStatus) {
		this.deletionStatus = deletionStatus;
	}
	
	
	public Message(HashMap<String, Object> map ) {
		super(map);
		setTableName("Message");
		init();
		
	}
	public void init() {
		
		
	}
	
	
	
}
