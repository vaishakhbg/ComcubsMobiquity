package com.viva.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.dao.ICustomerDao;

import com.viva.entity.Customer;

@Service
public class CustomerService {
	private static final Logger log = LogManager.getLogger(CustomerService.class.getName());
	@Autowired
	private ICustomerDao icustomerdao;
	
	@Autowired
	private EntityManager em;

	public Optional<Customer> getCustomer(String phoneno) {
		try {
			Optional<Customer> customer = icustomerdao.findByPhoneNo(phoneno);
			log.info("View customer Response sent");
			return customer;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public boolean addCustomer(Customer customer) {
		try {
            Timestamp timestamp= new Timestamp(System.currentTimeMillis());
			customer.setCustomerID(timestamp.getTime());
			icustomerdao.save(customer);
			log.info(customer + ">>>>>>>>Customer added sucessfully<<<<<<<<<");
			return true;
		} catch (Exception e) {
			log.error(customer + ">>>>>>>Customer add request failed<<<<<<<<<<<<");
			log.error(e.getMessage());
			return false;
		}
	}

	

	public List<Customer> getAllCustomer() {
		try {
			return icustomerdao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	public boolean deleteCustomer(String phoneno) {

		try {
			
			icustomerdao.deleteByPhoneNo(phoneno);
			return true;
		} catch (Exception e) {
			log.error(">>>>>>>Customer delete request failed<<<<<<<<<<<<");
			log.error(e.getMessage());
			return false;
		}
	}

}
