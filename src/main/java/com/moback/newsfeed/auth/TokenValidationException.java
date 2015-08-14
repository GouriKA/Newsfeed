package com.moback.newsfeed.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenValidationException extends Exception {

	public TokenValidationException() {
		super();	
	}

	public TokenValidationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public TokenValidationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public TokenValidationException(String message) {
		super(message);
		
	}

	public TokenValidationException(Throwable cause) {
		super(cause);
		
	}

}
