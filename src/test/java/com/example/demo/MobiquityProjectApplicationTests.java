package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.viva.dao.CustomerDaoImpl;
import com.viva.dao.IAdminDao;
import com.viva.dao.ICustomerDao;
import com.viva.entity.Admin;
import com.viva.entity.Customer;
import com.viva.exceptions.CustomerNotFoundException;
import com.viva.exceptions.EmptyDatabaseException;
import com.viva.exceptions.IncorrectCredentialsException;
import com.viva.exceptions.PhoneNoExistsException;
import com.viva.service.AdminService;
import com.viva.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MobiquityProjectApplicationTests.class)
class MobiquityProjectApplicationTests {

	@InjectMocks
	private CustomerService customerservice;
	@InjectMocks
	private AdminService adminservice;
	@Mock
	private ICustomerDao customerdao;
	@Mock
	private CustomerDaoImpl customerdaoimpl;
	@Mock
	private IAdminDao admindao;

	Customer cus = new Customer(10002,"8888888888", "jas", "singh", "jas@gmail.com",'Y', "male", 21, "14", "singh",
			"singh", "singh", "kyc123","143001");
	Customer cus1 = new Customer(10002, "8888898888", "jas", "singh", "jas@gmail.com", 'Y', "male", 21, "14", "singh",
			"singh", "singh", "kyc123", "143001");
	Customer cus2 = new Customer(10003, "8888888889", "jas", "singh", "jas@gmail.com", 'Y', "male", 21, "14", "singh",
			"singh", "singh", "kyc623", "143001");

	ArrayList<Customer> custlist = new ArrayList<Customer>();
	@Test
	public void getCustomerTest() {
		String phone ="7777777777" ;
		
		when(customerdaoimpl.findByPhoneNoAndStatus(phone)).thenReturn(cus);
		try {
			assertEquals(cus, customerservice.getCustomer(phone));
		} catch (CustomerNotFoundException e) {
			
		}
	}

	@Test
	public void ValidateAdminPosTest() throws NoSuchAlgorithmException {
		Admin admin = new Admin("jaskaran","gghhjj");
		Admin addmin=new Admin("jaskaran","5428ed9edcd3614bd45e8f4260bff4f904831c6358162e0d33053a4c9bff6916");
		when(admindao.findById("jaskaran")).thenReturn(Optional.of(addmin));
		try {
			assertEquals(true, adminservice.adminValidation(admin));
		} catch (IncorrectCredentialsException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	public void ValidateAdminNegTest() throws NoSuchAlgorithmException {
		Admin admin = new Admin("jaskaran","gghhjj");
		Admin addmin=new Admin("jaskaran","gghhjj");
	when(admindao.findById("jaskaran")).thenReturn(Optional.of(addmin));
		try {
			assertEquals(false, adminservice.adminValidation(admin));
		} catch (IncorrectCredentialsException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	
	@Test
	public void AddCustomerTest() {
		String phone= "8888888888";
		when(customerdaoimpl.findByPhoneNoAndStatus(phone)).thenReturn(null);
		try {
			assertEquals(true,customerservice.addCustomer(cus) );
		} catch (PhoneNoExistsException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	public void GetAllCustomerTest() {
		custlist.add(cus1);
		custlist.add(cus2);
		when(customerdao.findAll()).thenReturn(custlist);
		try {
			assertEquals(custlist, customerservice.getAllCustomer());
		} catch (EmptyDatabaseException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	

}
