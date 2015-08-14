package com.moback.newsfeed.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="textfeeds")
public class TextFeed {

	@Id
	private String id;
	private String msg;
	private String userId;
	private String firstName;
	private String lastName;
	private long timeStamp;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStampCreated) {
		this.timeStamp = timeStampCreated;
	}
	
	

	public void setId(String id) {
		this.id = id;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String author) {
		this.userId = author;
	}

	public String getId() {
		return id;
	}
	public String getMsg() {
		return msg;
	}
	
}
