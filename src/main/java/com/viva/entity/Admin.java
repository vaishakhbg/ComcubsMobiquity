package com.viva.entity;

import javax.persistence.Entity;

import javax.persistence.Id;

import com.google.gson.Gson;

@Entity
public class Admin {

	@Id
	private String username;
	private String password;



	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	

	public Admin() {
		super();
	}

	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [  username=" + username + ", password=" + password + "]";
	}
	
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

}
