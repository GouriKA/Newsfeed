package com.moback.newsfeed.validation;

public class ValidationMessage {

	private String propertyName;
	private String message;

	
	public ValidationMessage(String propertyName, String message) {
		super();
		this.propertyName = propertyName;
		this.message = message;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
