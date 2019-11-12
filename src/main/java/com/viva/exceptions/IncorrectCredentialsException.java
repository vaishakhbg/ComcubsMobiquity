package com.viva.exceptions;

public class IncorrectCredentialsException extends Exception{

	public IncorrectCredentialsException(Exception ex) {
		super(ex);
	}
	
	public IncorrectCredentialsException(Throwable t) {
		super(t);
	}
	
	public IncorrectCredentialsException(String message) {
		super(message);
	}
}
