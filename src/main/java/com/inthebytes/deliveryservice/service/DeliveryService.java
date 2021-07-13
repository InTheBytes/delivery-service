package com.inthebytes.deliveryservice.service;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inthebytes.deliveryservice.dao.DeliveryDao;
import com.inthebytes.deliveryservice.dao.DriverDao;
import com.inthebytes.deliveryservice.dto.DeliveryDto;
import com.inthebytes.deliveryservice.dto.DriverDto;
import com.inthebytes.deliveryservice.entity.Delivery;
import com.inthebytes.deliveryservice.entity.Driver;

@Service
public class DeliveryService {
	
	@Autowired
	DriverDao driverRepo;
	
	@Autowired
	DeliveryDao deliveryRepo;
	
	@Autowired
	EntityMapper mapper;
	
	public DeliveryDto start(String driverId, String orderId) {
		Driver d = driverRepo.getOne(driverId);
		d.setStatus(d.STATUS_PICKING_UP);
		driverRepo.save(d);
		Delivery del = new Delivery();
		del.setDriverId(driverId);
		del.setOrderId(orderId);
		del.setStartTime(Time.valueOf(LocalTime.now()));
		deliveryRepo.save(del);
		DeliveryDto dto = mapper.mapDelivery(del);
		return dto;
	}

	public DeliveryDto pickup(String deliveryId) {
		Delivery del = deliveryRepo.getOne(deliveryId);
		Driver d = driverRepo.getOne(del.getDriverId());
		d.setStatus(d.STATUS_DELIVERING);
		driverRepo.save(d);
		del.setPickupTime(Time.valueOf(LocalTime.now()));
		deliveryRepo.save(del);
		DeliveryDto dto = mapper.mapDelivery(del);
		return dto;
	}

	public DeliveryDto delivered(String deliveryId) {
		Delivery del = deliveryRepo.getOne(deliveryId);
		Driver d = driverRepo.getOne(del.getDriverId());
		d.setStatus(d.STATUS_IDLE);
		driverRepo.save(d);
		del.setDeliverTime(Time.valueOf(LocalTime.now()));
		deliveryRepo.save(del);
		DeliveryDto dto = mapper.mapDelivery(del);
		return dto;
	}

	public DriverDto checkIn(String driverId) {
		Driver d = driverRepo.getOne(driverId);
		d.setStatus(d.STATUS_IDLE);
		driverRepo.save(d);
		DriverDto dto = mapper.mapDriver(d);
		return dto;
	}

	public DriverDto checkOut(String driverId) {
		Driver d = driverRepo.getOne(driverId);
		d.setStatus(d.STATUS_OFFLINE);
		driverRepo.save(d);
		DriverDto dto = mapper.mapDriver(d);
		return dto;
	}

	public DriverDto newDriver(Driver driver) {
		Driver d = driver;
		driverRepo.save(d);
		DriverDto dto = mapper.mapDriver(d);
		return dto;
	}

	public DriverDto editDriver(Driver driver) {
		Driver d = driver;
		driverRepo.save(d);
		DriverDto dto = mapper.mapDriver(d);
		return dto;
	}

}
