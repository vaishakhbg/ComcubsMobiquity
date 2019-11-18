package com.viva.controller;

import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viva.entity.Admin;
import com.viva.exceptions.IncorrectCredentialsException;
import com.viva.service.AdminService;

import com.viva.util.ApiSuccess;

@CrossOrigin
@RestController
public class AdminController {
	private static final Logger log = LogManager.getLogger(AdminController.class.getName());
	
	private ResponseEntity<Object> buildResponseEntity(ApiSuccess apiSuccess) {
		return new ResponseEntity<>(apiSuccess, apiSuccess.getStatus());
	}
	
	@Autowired
	private AdminService adminservice;

	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public ResponseEntity<Object> adminValidation(@RequestBody Admin admin)
			throws NoSuchAlgorithmException, IncorrectCredentialsException {
		boolean status=adminservice.adminValidation(admin);
		ApiSuccess apiSuccess = new ApiSuccess(HttpStatus.ACCEPTED);
		apiSuccess.setMessage("Successful login");
		return buildResponseEntity(apiSuccess);
	}

}
