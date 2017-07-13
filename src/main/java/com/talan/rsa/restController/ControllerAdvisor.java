package com.talan.rsa.restController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.talan.rsa.entity.other.Error;
import com.talan.rsa.exception.EntityNotFoundException;

@ControllerAdvice
@RestController //just to avoid @ResponseBody in every method
public class ControllerAdvisor {
	
	private final int NOT_FOUND_STATUS = 404;
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error themeNotFound(EntityNotFoundException e){
		return new Error(NOT_FOUND_STATUS, e.getEntity() + " with id " + e.getThemeId() + " was not found");
	}
}
