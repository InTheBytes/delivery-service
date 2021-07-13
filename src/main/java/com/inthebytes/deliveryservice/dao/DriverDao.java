package com.inthebytes.deliveryservice.dao;

import org.springframework.stereotype.Repository;

import com.inthebytes.deliveryservice.entity.Driver;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DriverDao extends JpaRepository<Driver, String> {
	
}
