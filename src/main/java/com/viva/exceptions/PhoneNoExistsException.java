package com.viva.exceptions;

public class PhoneNoExistsException extends Exception{
	
	public PhoneNoExistsException(Exception ex) {
		super(ex);
	}
	
	public PhoneNoExistsException(String message) {
		super(message);
	}
	
	public PhoneNoExistsException(Throwable t) {
		super(t);
	}

}
