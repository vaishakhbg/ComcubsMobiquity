package com.viva.entity;



import javax.persistence.Entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.Gson;

@Entity
public class Customer {

	@Id
	/* @GeneratedValue(strategy = GenerationType.AUTO) */
	private double customerID;

	
	@NotNull
	private String phoneNo;
	
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	private String email;
	@NotNull
	private char Status;
	@NotNull
	private String gender;
	@NotNull
	private int age ;
	@NotNull
	@Size(max = 100)
	private String houseNo;
	@NotNull
	@Size(max = 100)
	private String street;
	@NotNull
	@Size(max = 100)
	private String city;

	@NotNull
	@Size(max = 100)
	private String state;
	@NotNull
	@Size(max=20)
	private String kycID ;
	
	@NotNull
	@Size(max = 6)
	private String pincode;

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public Customer() {
			}

	public double getCustomerID() {
		return customerID;
	}

	public void setCustomerID(double customerID) {
		this.customerID = customerID;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		this.Status = status;
	}



	public String getKycID() {
		return kycID;
	}

	public void setKycID(String kycID) {
		this.kycID = kycID;
	}

	
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public Customer(double customerID, @NotNull String phoneNo, @NotNull String firstname, @NotNull String lastname,
			@NotNull String email, @NotNull char Status, @NotNull String gender, @NotNull int age,
			@NotNull @Size(max = 100) String houseNo, @NotNull @Size(max = 100) String street,
			@NotNull @Size(max = 100) String city, @NotNull @Size(max = 100) String state,
			@NotNull @Size(max = 20) String kycID, @NotNull @Size(max = 6) String pincode) {
		super();
		this.customerID = customerID;
		this.phoneNo = phoneNo;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.Status = Status;
		this.gender = gender;
		this.age = age;
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.kycID = kycID;
		this.pincode = pincode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	@Override
	public String toString() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
	

	
	
}
