package com.example.rest.commons.exception;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;


@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(IllegalResourceContentException.class)
	public HttpEntity<VndErrors> handleBadRequestException(IllegalResourceContentException exception) {
		return doHandleException(exception, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	protected HttpEntity<VndErrors> handleBadRequestException(ResourceNotFoundException exception) {
		return doHandleException(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	protected HttpEntity<VndErrors> entityNotFound(HttpStatusCodeException exception){
		return doHandleException(exception, exception.getStatusCode());
	}
	
	private HttpEntity<VndErrors> doHandleException(Exception e, HttpStatus httpStatus) {
		VndErrors vndErrors = new VndErrors(new VndErrors.VndError(e.getClass().getName(), e.getMessage()));
		return new ResponseEntity<VndErrors>(vndErrors, httpStatus);
	}
}
