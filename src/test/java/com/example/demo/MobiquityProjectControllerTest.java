//package com.example.demo;
//
//import java.awt.PageAttributes.MediaType;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.viva.MobiquityProjectApplication;
//import com.viva.controller.CustomerController;
//import com.viva.entity.Customer;
//import com.viva.service.CustomerService;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = CustomerController.class)
//@ContextConfiguration(classes = { MobiquityProjectApplication.class })
//public class MobiquityProjectControllerTest {
//	@Autowired
//	private MockMvc mockMvc;
//	@MockBean
//	private CustomerService customerservice;
//
//	Customer cus = new Customer(10002, "8888888888", "jas", "singh", "jas@gmail.com", "male", 21, "14", "singh",
//			"singh", "singh", "143001");
//
//	@Test
//	public void getCustomerByIdTest() throws Exception {
//		Mockito.when(customerservice.getCustomer(Mockito.anyString())).thenReturn(Optional.of(cus));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/view/8888888888")
//				.accept(org.springframework.http.MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println(result.getResponse());
//		String expected = "{customerID:10002,phoneNo:'8888888888',firstname:jas,lastname:singh,email:jas@gmail.com,gender:male,age:21,houseNo:'14',street:singh,city:singh,state:singh,pincode:'143001'}";
//		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
//	}
//
//}
