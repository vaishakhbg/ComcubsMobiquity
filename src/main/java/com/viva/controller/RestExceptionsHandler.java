package com.viva.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.viva.util.ApiError;
import com.viva.exceptions.CustomerNotFoundException;
import com.viva.exceptions.EmptyDatabaseException;
import com.viva.exceptions.IncorrectCredentialsException;
import com.viva.exceptions.PhoneNoExistsException;


@ControllerAdvice

public class RestExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(IncorrectCredentialsException.class)
	public ResponseEntity<Object> handleIncorrectCredentialsException(IncorrectCredentialsException ex){
		ApiError apiError= new ApiError(HttpStatus.UNAUTHORIZED);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);	
	}
	
	@ExceptionHandler({CustomerNotFoundException.class , EmptyDatabaseException.class})
	public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex){
		ApiError apiError= new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(PhoneNoExistsException.class)
	public ResponseEntity<Object> handlePhoneNoExistsException(PhoneNoExistsException ex){
		ApiError apiError= new ApiError(HttpStatus.CONFLICT);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	
	

}
