package com.moback.newsfeed.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moback.newsfeed.Constants;
import com.moback.newsfeed.auth.AuthToken;
import com.moback.newsfeed.auth.AuthorizationService;
import com.moback.newsfeed.http.TextFeedJSON;
import com.moback.newsfeed.model.TextFeed;
import com.moback.newsfeed.repository.TextFeedRepositoryImpl;
import com.moback.newsfeed.validation.TextValidator;

@RestController
public class TextFeedController {

	private TextFeedRepositoryImpl textFeedRepository;

	@Autowired
	private AuthorizationService authorizationService;
	
	@Autowired
	private TextValidator textValidator;

	@Autowired
	public TextFeedController(TextFeedRepositoryImpl textFeedRepository) {
		this.textFeedRepository = textFeedRepository;
	}

	@RequestMapping(value = "/newsfeed/user_post", method = RequestMethod.GET)
	public ListTextsResponse newsFeedHomePage() {
		return new ListTextsResponse(textFeedRepository.getAllTextFeeds());
	}

	@RequestMapping(value = "/newsfeed/user_post/{user-id}", method = RequestMethod.GET)
	public ListTextsResponse getTextFeedsOfUser(
			@PathVariable(value = "user-id") String userId, HttpServletRequest request, HttpServletResponse response) {
		String authTokenString = request.getHeader(Constants.AUTH_TOKEN_HTTP_HEADER);
		AuthToken authToken = authorizationService.assertUser(authTokenString);
		if(authToken == null || !authToken.getUserId().equals(userId)) {
			throw new AuthenticationFailedException(userId);
		}
		  else {
			  return new ListTextsResponse(textFeedRepository.getTextFeedsOfUser(userId));
		}
	}

	@RequestMapping(value = "/newsfeed/user_post/{user-id}", method = RequestMethod.POST)
	public void newPost(@RequestBody TextFeedJSON textFeedJSON,
			@PathVariable(value = "user-id") String userId, HttpServletRequest request, HttpServletResponse response) {

		/*if(!userId.equals(request.getAttribute("userId"))) {
			throw new AuthenticationFailedException(userId);
		}*/
		String authTokenString = request.getHeader(Constants.AUTH_TOKEN_HTTP_HEADER);
		AuthToken authToken = authorizationService.assertUser(authTokenString);
		if(authToken == null || !authToken.getUserId().equals(userId)) {
			throw new AuthenticationFailedException(userId);
		} 
		else {
			textValidator.validate(textFeedJSON, false, false);
			TextFeed textFeed = new TextFeed();
			textFeed.setUserId(userId);
			textFeed.setFirstName(textFeedJSON.getUserFirstName());
			textFeed.setLastName(textFeedJSON.getUserLastName());
			textFeed.setMsg(textFeedJSON.getMsg());
			textFeed.setTimeStamp(System.currentTimeMillis());
			textFeedRepository.addNewPost(textFeed);
		}
	}

	@RequestMapping(value = "/newsfeed/user_post/update/{user-id}", method = RequestMethod.POST)
	public void updatePost(@RequestBody TextFeedJSON textFeedJSON,
			@PathVariable(value = "user-id") String userId, HttpServletRequest request, HttpServletResponse response) {
				
		/*if(!userId.equals(request.getAttribute("userId"))) {
			throw new AuthenticationFailedException(userId);
		}*/
		String authTokenString = request.getHeader(Constants.AUTH_TOKEN_HTTP_HEADER);
		AuthToken authToken = authorizationService.assertUser(authTokenString);
		if(authToken == null || !authToken.getUserId().equals(userId)) {
			throw new AuthenticationFailedException(userId);
		}		  
		else {
			textValidator.validate(textFeedJSON, true, false);
			TextFeed textFeed = new TextFeed();
			textFeed.setUserId(userId);
			textFeed.setId(textFeedJSON.getTextId());
			textFeed.setMsg(textFeedJSON.getMsg());
			textFeed.setTimeStamp(System.currentTimeMillis());
			textFeedRepository.updatePost(textFeed);
		}
	}

	@RequestMapping(value = "/newsfeed/user_post/{user-id}/{text-id}", method = RequestMethod.DELETE)
	public void deletePost(@PathVariable(value = "user-id") String userId,
			@PathVariable(value = "text-id") String textId, HttpServletRequest request, HttpServletResponse response) {
		
		/*if(!userId.equals(request.getAttribute("userId"))) {		
			throw new AuthenticationFailedException(userId);
		}*/
		String authTokenString = request.getHeader(Constants.AUTH_TOKEN_HTTP_HEADER);
		AuthToken authToken = authorizationService.assertUser(authTokenString);
		if(authToken == null || !authToken.getUserId().equals(userId)) {
			throw new AuthenticationFailedException(userId);
		}		  
		else {
			TextFeedJSON textFeedJSON = new TextFeedJSON();
			textFeedJSON.setTextId(textId);
			textValidator.validate(textFeedJSON, false, true);
			textFeedRepository.deletePost(userId, textId);
		}
	}
	
	class ListTextsResponse {
		private List<TextFeed> texts;
		public ListTextsResponse(List<TextFeed> texts) {
			super();
			this.texts = texts;
		}
		public List<TextFeed> getTexts() {
			return texts;
		}
		public void setTexts(List<TextFeed> texts) {
			this.texts = texts;
		}
	}
	
	@SuppressWarnings("serial")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	class AuthenticationFailedException extends RuntimeException {
		public AuthenticationFailedException(String userId) {
			super("User name and/or password is incorrect. Please try to login again with correct username and password");
		}
	}
}
