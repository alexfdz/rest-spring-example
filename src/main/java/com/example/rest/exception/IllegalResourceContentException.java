package com.example.rest.exception;


public class IllegalResourceContentException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public IllegalResourceContentException(String message){
		super(message);
	}

}
