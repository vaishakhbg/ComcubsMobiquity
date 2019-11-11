package com.viva.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viva.entity.Customer;

import com.viva.service.CustomerService;

@CrossOrigin
@RestController
@Transactional
public class CustomerController {
	private static final Logger log = LogManager.getLogger(CustomerController.class.getName());

	@Autowired
	private CustomerService customerservice;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public boolean addCustomer(@RequestBody Customer customer) {
		log.info("Add customer post request is recieved for customer with phone no :"+customer.getPhoneNo());
		return customerservice.addCustomer(customer);
	}

	@RequestMapping("/view/{phoneno}")
	public Optional<Customer> getCustomer(@PathVariable String phoneno) {
			log.info("view customer request is recieved for Customer with phone no. :"+phoneno);
		return customerservice.getCustomer(phoneno);
	}
	
	@RequestMapping("/viewall")
	public List<Customer> getAllCustomer(){
		log.info("view all customer request recieved");
		return customerservice.getAllCustomer();
			}
	
	@RequestMapping("/delete/{phoneno}")
		public boolean deleteCustomer(@PathVariable String phoneno) {
			log.info("delete request recieved for customer with phone no :"+ phoneno);
			return customerservice.deleteCustomer(phoneno);
		}
	}


