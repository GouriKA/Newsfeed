package com.moback.newsfeed.validation;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moback.newsfeed.http.TextFeedJSON;
import com.moback.newsfeed.repository.TextFeedRepository;

@Component
public class TextValidator {
	
	@Autowired
	private TextFeedRepository textRepository;
	
	public void validate(TextFeedJSON text, boolean validateForUpdation, boolean validateForDeletion) {
		
		ArrayList<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		
		if(validateForDeletion) {
			if(!textRepository.isTextAvailable(text.getTextId())) {
				messages.add(new ValidationMessage("textId", "Text you are trying to delete is not found"));
			}
		}
		else {
			if(text == null ) {
				throw new IllegalArgumentException("Text field can't be null");
			}
			else if (StringUtils.isBlank(text.getMsg()))
				messages.add(new ValidationMessage("textmsg", "Text field can't be empty"));
			else {
				if(validateForUpdation && !textRepository.isTextAvailable(text.getTextId())) {
					messages.add(new ValidationMessage("textId", "Text you are trying to update is not found"));
				}
			}
		}
						
		if(messages.size() > 0) {
			throw new ValidationFailedException(messages);
		}
	}

}
