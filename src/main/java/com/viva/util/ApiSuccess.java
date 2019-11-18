package com.viva.util;



import org.springframework.http.HttpStatus;


import com.viva.entity.Customer;

public class ApiSuccess {

	private HttpStatus status;
	private String message;

	private ApiSuccess() {
		
	}

	

	public ApiSuccess(HttpStatus status) {
		this();
		this.status = status;
		this.message = "Successful";
		
	}

	public ApiSuccess(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public void setMessage(Customer customer) {
		this.message=customer.toString();
		
	}



	
}
