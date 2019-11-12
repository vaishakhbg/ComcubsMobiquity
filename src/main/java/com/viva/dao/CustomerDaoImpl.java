package com.viva.dao;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


import com.viva.entity.Customer;

/**
 * Abstract dao implementations, provides view By from and to date, and view by
 * month.
 * 
 * @author vaishak
 *
 * @param <T>
 */
@Repository
public class CustomerDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;


	
	
	public Customer findByPhoneNoAndStatus(String phoneNo) {
		String querystr="FROM Customer WHERE phone_no=:phoneno AND status='Y'"; 
		Query query = entityManager.createQuery(querystr);
		query.setParameter("phoneno",phoneNo);
		try {
			return (Customer) query.getSingleResult();
		}
		catch (NoResultException nre){
			return null;
		}
		
		}
		
	

	
}
