package com.moback.newsfeed.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moback.newsfeed.Constants;
import com.moback.newsfeed.auth.AuthorizationService;
import com.moback.newsfeed.auth.AuthorizationSignature;
import com.moback.newsfeed.http.UserJSON;
import com.moback.newsfeed.model.User;
import com.moback.newsfeed.repository.UserRepositoryImpl;
import com.moback.newsfeed.validation.UserValidator;

@RestController
public class UserController {

	private UserRepositoryImpl userRepository;
	private long DEFAULT_TOKEN_EXPIRATION = 14400000; // 4 hrs
	
	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private AuthorizationSignature authSignature;
	
	@Autowired
	private UserValidator userValidator;

	@Autowired
	public UserController(UserRepositoryImpl userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/newsfeed/login", method = RequestMethod.PUT)
	public LoginResponse login(@RequestBody UserJSON userJSON, HttpServletResponse response) {
		userValidator.validate(userJSON, false);
		UserJSON userJSONResponse = userRepository.authenticate(userJSON);
		if (userJSONResponse.getId() != null) {
			String authToken = authSignature.createAuthTokenString(
					userJSONResponse.getId(), System.currentTimeMillis() + DEFAULT_TOKEN_EXPIRATION);
			return new LoginResponse(authToken, userJSONResponse);
		} 
		else {
			throw new AuthenticationFailedException(userJSON.getUsername());
		}
	}

	@RequestMapping(value = "/newsfeed/user", method = RequestMethod.GET)
	public ListUsersResponse getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		String authTokenString = request.getHeader(Constants.AUTH_TOKEN_HTTP_HEADER);
		authorizationService.assertUser(authTokenString);	
		return new ListUsersResponse(userRepository.getAllUsers());
	}
	
	@RequestMapping(value = "/newsfeed/user/findByName", method = RequestMethod.PUT)
	public ListUsersResponse getUserInfoByName(@RequestBody UserJSON userJSON, HttpServletRequest request, HttpServletResponse response) {
		String authTokenString = request.getHeader(Constants.AUTH_TOKEN_HTTP_HEADER);
		authorizationService.assertUser(authTokenString);
		return new ListUsersResponse(userRepository.getUserInfoByName(userJSON.getFirstName()));
	}
	

	@RequestMapping(value = "/newsfeed/user", method = RequestMethod.POST)
	public LoginResponse newUser(@RequestBody UserJSON userJSON) {
		userValidator.validate(userJSON, true);
		userRepository.addNewUser(userJSON);
		UserJSON userJSONResponse = userRepository.authenticate(userJSON);
		String authToken = authSignature.createAuthTokenString(
				userJSONResponse.getId(), System.currentTimeMillis() + DEFAULT_TOKEN_EXPIRATION);
		return new LoginResponse(authToken, userJSONResponse);
	}
		
	public class ListUsersResponse {
		private List<User> users;
		public ListUsersResponse(List<User> users) {
			super();
			this.users = users;
		}
		public List<User> getUsers() {
			return users;
		}
		public void setUsers(List<User> users) {
			this.users = users;
		}
	}
	
	public class LoginResponse {
	
		private String authToken;
		private String userId;
		private String firstName;
		private String lastName;
		
		public LoginResponse(String authToken, UserJSON userJSON) {
			this.authToken = authToken;
			this.userId = userJSON.getId();
			this.firstName = userJSON.getFirstName();
			this.lastName = userJSON.getLastName();
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getAuthToken() {
			return authToken;
		}
		
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

		public void setAuthToken(String authToken) {
			this.authToken = authToken;
		}
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	class AuthenticationFailedException extends RuntimeException {
		public AuthenticationFailedException(String username) {
			super("User name and/or password is incorrect. Please try to login again with correct username and password");
		}
	}

}
