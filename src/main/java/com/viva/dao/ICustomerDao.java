package com.viva.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viva.entity.Customer;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Double>{
	
	public Optional<Customer> findByPhoneNo(String phoneno);
	
	public Optional<Boolean> deleteByPhoneNo(String phoneno);
	/*
	 * @Query(value="Select * from customer order by customerID desc limit 1"
	 * ,nativeQuery=true) public Customer LastCustomer();
	 */
	

}
