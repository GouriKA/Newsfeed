package com.moback.newsfeed.auth;

public class AuthToken {
	private String userId;
	private long expirationTime;
	private String signature;
	private String valueString;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public long getExpirationTime() {
		return expirationTime;
	}
	
	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
		
}