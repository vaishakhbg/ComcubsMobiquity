package com.viva.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viva.dao.IAdminDao;
import com.viva.entity.Admin;
import com.viva.util.HashPwd;

@Service
public class AdminService {

	@Autowired
	private IAdminDao iadmindao;

	public boolean adminValidation(Admin model) throws NoSuchAlgorithmException {

		Optional<Admin> admin = iadmindao.findById(model.getUsername());
		Admin ad = admin.get();
		HashPwd hasher = new HashPwd();
		String hashed_pwd = hasher.HashingFunction(model.getPassword());
		System.out.println(hashed_pwd);
		if (hashed_pwd.equals(ad.getPassword())) {
			return true;
		}
		return false;
	}
	
}
