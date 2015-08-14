package com.moback.newsfeed.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class AuthorizationService {

	@Autowired
	private AuthorizationSignature authSignature;
	
	@SuppressWarnings("finally")
	public AuthToken assertUser(String authTokenString) {
		AuthToken authToken = null;
		try {
			authSignature.validateAuthToken(authTokenString);
			authToken = authSignature.parseAuthTokenString(authTokenString);
		} catch (TokenValidationException e) {
			throw new UnauthorizedAccess("Access denied");
		} finally {
			return authToken;
		}
	}
		
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	class UnauthorizedAccess extends RuntimeException {
		public UnauthorizedAccess() {
			super();
		}
		public UnauthorizedAccess(String message) {
			super(message);
			System.out.println(message);
		}
	}

}