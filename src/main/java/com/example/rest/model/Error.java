package com.example.rest.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Error {
	
	private String id;
	private String description;
	private String developerDescription;
	
	public Error(HttpStatus status, Exception exception){
		this.id = status.toString();
		this.description = status.getReasonPhrase();
		this.developerDescription = exception.getMessage();
	}

	public static ResponseEntity<Error> buildResponse(HttpStatus status, Exception exception){
		Error error = new Error(status, exception);
		return new ResponseEntity<Error>(error, status);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeveloperDescription() {
		return developerDescription;
	}

	public void setDeveloperDescription(String developerDescription) {
		this.developerDescription = developerDescription;
	}
}
