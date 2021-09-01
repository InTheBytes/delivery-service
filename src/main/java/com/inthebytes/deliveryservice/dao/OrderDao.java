package com.inthebytes.deliveryservice.dao;

import org.springframework.stereotype.Repository;

import com.inthebytes.deliveryservice.entity.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderDao extends JpaRepository<Delivery, String> {
	
}
