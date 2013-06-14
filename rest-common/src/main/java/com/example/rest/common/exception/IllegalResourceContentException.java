package com.example.rest.common.exception;


public class IllegalResourceContentException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public IllegalResourceContentException(String message){
		super(message);
	}

}
