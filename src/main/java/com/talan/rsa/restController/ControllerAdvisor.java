package com.talan.rsa.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.talan.rsa.entity.other.RsaResponseBody;
import com.talan.rsa.entity.other.ErrorMessageBuilder;
import com.talan.rsa.exception.EntityNotFoundException;

@ControllerAdvice
@RestController //just to avoid @ResponseBody in every method
public class ControllerAdvisor {
	
	private final int NOT_FOUND_STATUS = 404;
	
	@Autowired
	private ErrorMessageBuilder emb;
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public RsaResponseBody themeNotFound(EntityNotFoundException e){
		return new RsaResponseBody(NOT_FOUND_STATUS, emb.buildErrorMessage(e.getEntity(), e.getEntityIds()));
	}
}
