package com.example.demo;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viva.MobiquityProjectApplication;
import com.viva.controller.AdminController;
import com.viva.entity.Admin;
import com.viva.exceptions.IncorrectCredentialsException;
import com.viva.service.AdminService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminController.class)
@ContextConfiguration(classes = { MobiquityProjectApplication.class })

public class MobiquityProjectAdminControllerTest {

	@InjectMocks
	private AdminController admincontroller;

	@MockBean
	private AdminService adminservice;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	Admin admin = new Admin("sudhakar_bhat", "changeit");

	@Test
	public void adminLoginTest() throws Exception {
		String jsonRequest = om.writeValueAsString(admin);
		Mockito.when(adminservice.adminValidation(Mockito.any())).thenReturn(true);
		MvcResult result = mockMvc
				.perform(post("/adminlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		String resultContent = result.getResponse().getContentAsString();

		String expected = "{\"status\":\"ACCEPTED\",\"message\":\"Successful login\"}";
		System.out.println("*********************************************************************");
		System.out.println(result.getResponse().getContentAsString());
		System.out.println("*********************************************************************");
		assertEquals(expected, resultContent);
	}

	@Test
	public void adminLoginUsernameFailTest() throws Exception {
		String jsonRequest = om.writeValueAsString(admin);
		Mockito.when(adminservice.adminValidation(Mockito.any()))
				.thenThrow(new IncorrectCredentialsException("Incorrect username"));
		MvcResult result = mockMvc
				.perform(post("/adminlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		String resultContent = result.getResponse().getContentAsString();

		String expected = "{\"status\":\"UNAUTHORIZED\",\"message\":\"Incorrect username\"}";
		System.out.println("*********************************************************************");
		System.out.println(result.getResponse().getContentAsString());
		System.out.println("*********************************************************************");
		assertEquals(expected, resultContent);
	}
	
	@Test
	public void adminLoginPasswordFailTest() throws Exception {
		String jsonRequest = om.writeValueAsString(admin);
		Mockito.when(adminservice.adminValidation(Mockito.any()))
				.thenThrow(new IncorrectCredentialsException("Incorrect password"));
		MvcResult result = mockMvc
				.perform(post("/adminlogin").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		String resultContent = result.getResponse().getContentAsString();

		String expected = "{\"status\":\"UNAUTHORIZED\",\"message\":\"Incorrect password\"}";
		System.out.println("*********************************************************************");
		System.out.println(result.getResponse().getContentAsString());
		System.out.println("*********************************************************************");
		assertEquals(expected, resultContent);
	}

}
