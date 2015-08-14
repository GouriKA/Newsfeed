package com.moback.newsfeed.validation;

import java.util.List;

public class ValidationResponse {
	
	private List<ValidationMessage> messages;
	
	public ValidationResponse(List<ValidationMessage> messages) {
		this.messages = messages;
	}
	public List<ValidationMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ValidationMessage> messages) {
		this.messages = messages;
	}
	
}