package com.viva.controller;


import java.util.List;


import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viva.entity.Customer;
import com.viva.exceptions.CustomerNotFoundException;
import com.viva.exceptions.EmptyDatabaseException;
import com.viva.exceptions.PhoneNoExistsException;
import com.viva.service.CustomerService;
import com.viva.util.ApiError;
import com.viva.util.ApiSuccess;

@CrossOrigin
@RestController
@Transactional
public class CustomerController {
	private static final Logger log = LogManager.getLogger(CustomerController.class.getName());

	private ResponseEntity<Object> buildResponseEntity(ApiSuccess apiSuccess) {
		return new ResponseEntity<>(apiSuccess, apiSuccess.getStatus());
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@Autowired
	private CustomerService customerservice;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) throws PhoneNoExistsException {
		log.info("Add customer post request is recieved for customerv : "+customer);
		customer.setStatus('Y');
		if(customerservice.addCustomer(customer)==true) {
			ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.CREATED);
			apiSuccess.setMessage("Customer creation successful");
			return buildResponseEntity(apiSuccess);	
		}
		else {
			ApiError apiError = new ApiError(HttpStatus.CONFLICT);
			apiError.setMessage("Unknown error");
			return buildResponseEntity(apiError);		
		}
		
		
	}

	@RequestMapping("/view/{phoneno}")
	public ResponseEntity<Object> getCustomer(@PathVariable String phoneno) throws CustomerNotFoundException {
			log.info("view customer request is recieved for Customer with phone no. :"+phoneno);
			ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.OK);
			apiSuccess.setMessage(customerservice.getCustomer(phoneno));
			return buildResponseEntity(apiSuccess);
	}
	
	@RequestMapping("/viewall")
	public ResponseEntity<List<Customer>> getAllCustomer() throws EmptyDatabaseException{
		log.info("view all customer request recieved");
		return ResponseEntity.ok(customerservice.getAllCustomer());
		}
	
	@RequestMapping("/delete/{phoneno}")
		public boolean deleteCustomer(@PathVariable String phoneno) {
			log.info("delete request recieved for customer with phone no :"+ phoneno);
			return customerservice.deleteCustomer(phoneno);
		}
	}


