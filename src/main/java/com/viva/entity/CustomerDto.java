package com.viva.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDto {

	@NotNull
	private String phoneNo;
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	private String email;
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
	@Size(max = 6)
	private String pincode;
}
