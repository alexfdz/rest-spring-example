package com.example.rest.commons.exception;


public class IllegalResourceContentException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public IllegalResourceContentException(String message){
		super(message);
	}

}
