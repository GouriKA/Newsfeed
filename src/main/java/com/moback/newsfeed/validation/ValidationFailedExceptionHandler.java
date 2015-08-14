package com.moback.newsfeed.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice 
public class ValidationFailedExceptionHandler {

	@ExceptionHandler(ValidationFailedException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public ValidationResponse validationFailed(ValidationFailedException exception) {
		return new ValidationResponse(exception.getMessages());
	}
}
