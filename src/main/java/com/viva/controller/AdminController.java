package com.viva.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viva.entity.Admin;
import com.viva.service.AdminService;
import com.viva.service.CustomerService;

@CrossOrigin
@RestController
public class AdminController {
	 private static final Logger log = LogManager.getLogger(AdminController.class.getName());
	
	 @Autowired
	 private AdminService adminservice;
	 
	 
		@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
		public boolean adminValidation(@RequestBody Admin admin)   {
			try {
				log.info("Admin validation post request");
				return adminservice.adminValidation(admin);
			} catch (NoSuchAlgorithmException e) {
				log.error("Admin validation request failed");
				log.error(e.getMessage());
				e.printStackTrace();
				return false ;
			}
		}
		
	
}
