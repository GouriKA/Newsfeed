package com.moback.newsfeed.validation;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moback.newsfeed.http.UserJSON;
import com.moback.newsfeed.repository.UserRepository;

@Component
public class UserValidator {
	
	@Autowired
	private UserRepository userRepository;
	
	public void validate(UserJSON user, boolean validateForCreation) {
		
		ArrayList<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		
		if(user == null) {
			throw new IllegalArgumentException("User can't be null");
		}
		else {
			if(StringUtils.isBlank(user.getUsername())) {
				messages.add(new ValidationMessage("username", "User name can't be empty"));
			}
			if(StringUtils.isBlank(user.getPassword())) {
				messages.add(new ValidationMessage("password", "Password can't be empty"));
			}
		}
		if(validateForCreation) {
			if(!userRepository.isUserAvailable(user.getUsername())) {
				messages.add(new ValidationMessage("userName", "The user name specified is already taken"));
			}
			if(StringUtils.isBlank(user.getFirstName())) {
				messages.add(new ValidationMessage("userName", "First Name cannot be empty"));
			}
			if(StringUtils.isBlank(user.getLastName())) {
				messages.add(new ValidationMessage("userName", "Last Name cannot be empty"));
			}
			/*if(StringUtils.isBlank(String.valueOf(user.getAge())) || user.getAge() > 150) {
				messages.add(new ValidationMessage("userName", "Please specify your age"));
			}
			else if(user.getAge() < 15){
				messages.add(new ValidationMessage("userName", "User should be minimum of 15 years of age to sign in."));
			}*/
		}
				
		if(messages.size() > 0) {
			throw new ValidationFailedException(messages);
		}
	}
}
