package com.viva.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viva.entity.Admin;

@Repository
public interface IAdminDao extends JpaRepository<Admin,String> {

}
