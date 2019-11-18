package com.viva.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.viva.entity.Customer;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Double>{
	
	
	
	public Customer findByPhoneNo(String phoneno);
	public Boolean existsByPhoneNo(String phoneno);
	public Optional<Boolean> deleteByPhoneNo(String phoneno);
	

}
