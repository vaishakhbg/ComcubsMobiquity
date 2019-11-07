package com.example.demo;

import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.viva.dao.IAdminDao;
import com.viva.dao.ICustomerDao;
import com.viva.entity.Admin;
import com.viva.entity.Customer;
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
	private IAdminDao admindao;

	@Test
	public void getCustomerTest() {
		String phone = "7777777777";
		Customer cus = new Customer(10002, "8888888888", "jas", "singh", "jas@gmail.com", "male", 21, "14", "singh",
				"singh", "singh", "143001");
		when(customerdao.findByPhoneNo(phone)).thenReturn(Optional.of(cus));
		assertEquals(Optional.of(cus), customerservice.getCustomer(phone));
	}

	@Test
	public void ValidateAdminPosTest() throws NoSuchAlgorithmException {
		Admin admin = new Admin("jaskaran","gghhjj");
		Admin addmin=new Admin("jaskaran","5428ed9edcd3614bd45e8f4260bff4f904831c6358162e0d33053a4c9bff6916");
		when(admindao.findById("jaskaran")).thenReturn(Optional.of(addmin));
		assertEquals(true, adminservice.adminValidation(admin));
	}
	
	@Test
	public void ValidateAdminNegTest() throws NoSuchAlgorithmException {
		Admin admin = new Admin("jaskaran","gghhjj");
		Admin addmin=new Admin("jaskaran","gghhjj");
		when(admindao.findById("jaskaran")).thenReturn(Optional.of(addmin));
		assertEquals(false, adminservice.adminValidation(admin));
	}

}
