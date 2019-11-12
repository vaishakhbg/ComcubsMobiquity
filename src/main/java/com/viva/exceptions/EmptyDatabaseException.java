package com.viva.exceptions;

public class EmptyDatabaseException extends Exception{
	
	public EmptyDatabaseException(Exception ex) {
		super(ex);
	}
	
	public EmptyDatabaseException(String message) {
		super(message);
	}
	public EmptyDatabaseException(Throwable t) {
		super(t);
	}

}
