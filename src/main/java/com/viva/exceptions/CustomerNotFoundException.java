package com.viva.exceptions;

public class CustomerNotFoundException extends Exception {
	public CustomerNotFoundException(Exception ex) {
		super(ex);
	}

	public CustomerNotFoundException(Throwable t) {
		super(t);
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
