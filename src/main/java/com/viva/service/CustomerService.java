package com.viva.service;


import java.sql.Timestamp;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.dao.CustomerDaoImpl;
import com.viva.dao.ICustomerDao;

import com.viva.entity.Customer;
import com.viva.exceptions.CustomerNotFoundException;
import com.viva.exceptions.EmptyDatabaseException;
import com.viva.exceptions.PhoneNoExistsException;

@Service
public class CustomerService {
	private static final Logger log = LogManager.getLogger(CustomerService.class.getName());
	@Autowired
	private ICustomerDao icustomerdao;
	
	@Autowired
	private CustomerDaoImpl customerdaoimpl;
	

	public Customer getCustomer(String phoneno) throws CustomerNotFoundException {
			Customer customer = customerdaoimpl.findByPhoneNoAndStatus(phoneno);
			if (customer==null) {
				throw new CustomerNotFoundException("No customer found with phone number "+phoneno);
			}
			log.info("View customer Response sent for customer with phone no :"+phoneno);
			return customer;
		} 
	

	public char getStatus(String phoneno) {
		if(customerdaoimpl.findByPhoneNoAndStatus(phoneno) != null)
			return 'Y';
		else
			return 'N';
	}
	

	public boolean addCustomer(Customer customer) throws PhoneNoExistsException {
		if(getStatus(customer.getPhoneNo())=='Y') {
			throw new PhoneNoExistsException("Active customer with the same phone number exists");
		}
        Timestamp timestamp= new Timestamp(System.currentTimeMillis());
		customer.setCustomerID(timestamp.getTime());
		icustomerdao.save(customer);
		log.info(customer + ">>>>>>>>Customer added sucessfully<<<<<<<<<");
		return true;
	}

	

	public List<Customer> getAllCustomer() throws EmptyDatabaseException {
		if(icustomerdao.findAll().isEmpty()) {
			throw new EmptyDatabaseException("No users in database");
		}
		return icustomerdao.findAll();
	}



}
