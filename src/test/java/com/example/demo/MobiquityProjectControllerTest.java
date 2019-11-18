package com.example.demo;


import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viva.MobiquityProjectApplication;

import com.viva.controller.CustomerController;
import com.viva.entity.Admin;
import com.viva.entity.Customer;

import com.viva.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
@ContextConfiguration(classes = { MobiquityProjectApplication.class })
public class MobiquityProjectControllerTest {

	@InjectMocks
	private CustomerController customercontroller;

	@MockBean
	private CustomerService customerservice;

	ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	Customer cus = new Customer(10002, "8888898888", "jas", "singh", "jas@gmail.com", 'Y', "male", 21, "14", "singh",
			"singh", "singh", "kyc123", "143001");
	Customer cus1 = new Customer(10003, "8888888889", "jas", "singh", "jas@gmail.com", 'Y', "male", 21, "14", "singh",
			"singh", "singh", "kyc623", "143001");

	Admin admin = new Admin("sudhakar_bhat", "changeit");

	ArrayList<Customer> custlist = new ArrayList<Customer>();

	@Test
	public void getApiTestfail() throws Exception {
		MvcResult result = mockMvc.perform(get("/findall").content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound()).andReturn();
	}

	@Test
	public void getAllCustomerTest() throws Exception {
		custlist.add(cus);
		custlist.add(cus1);
		Mockito.when(customerservice.getAllCustomer()).thenReturn(custlist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/viewall")
				.accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String customerresult = result.getResponse().getContentAsString();
		Customer[] customerlist = om.readValue(customerresult, Customer[].class);
		assertTrue(customerlist.length > 0);

	}

	@Test
	public void getCustomerByIdTest() throws Exception {
		Mockito.when(customerservice.getCustomer(Mockito.anyString())).thenReturn(cus);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/view/8888888888")
				.accept(org.springframework.http.MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "{\r\n" + "    \"status\": \"OK\",\r\n"
				+ "    \"message\": \"{\\\"customerID\\\":10002.0,\\\"phoneNo\\\":\\\"8888898888\\\",\\\"firstname\\\":\\\"jas\\\",\\\"lastname\\\":\\\"singh\\\",\\\"email\\\":\\\"jas@gmail.com\\\",\\\"status\\\":\\\"Y\\\",\\\"gender\\\":\\\"male\\\",\\\"age\\\":21,\\\"houseNo\\\":\\\"14\\\",\\\"street\\\":\\\"singh\\\",\\\"city\\\":\\\"singh\\\",\\\"state\\\":\\\"singh\\\",\\\"kycID\\\":\\\"kyc123\\\",\\\"pincode\\\":\\\"143001\\\"}\"\r\n"
				+ "}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void addCustomerTest() throws Exception {

		Mockito.when(customerservice.addCustomer(Mockito.any())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON)
				.content(cus.toString()).accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"status\":\"CREATED\",\"message\":\"Customer creation successful\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void addCustomerConflictTest() throws Exception {
		Mockito.when(customerservice.addCustomer(Mockito.any())).thenReturn(false);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON)
				.content(cus.toString()).accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"status\":\"CONFLICT\",\"message\":\"Unknown error\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

}
