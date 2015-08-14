package com.moback.newsfeed.validation;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ValidationFailedException extends RuntimeException {
	
	private List<ValidationMessage> messages;
	
	public ValidationFailedException(List<ValidationMessage> messages) {
		super("Validation failed");
		this.messages = messages;
	}
	public ValidationFailedException(ValidationMessage message) {
		super("Validation failed");
		this.messages = new ArrayList<ValidationMessage>() ;
		this.messages.add(message);
	}
	public List<ValidationMessage> getMessages() {
		return this.messages;
	}
}