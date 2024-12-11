package com.fundoo.gateway.exception;


public class UnautorizedException extends RuntimeException{

	private String message;

	public UnautorizedException()
	{
		
	}
	
	public UnautorizedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
	
	
	
}
