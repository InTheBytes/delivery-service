package com.inthebytes.deliveryservice.entity;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable {

	static final long serialVersionUID = -1004750221236911383L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	    name = "UUID",
	    strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "delivery_id")
	private String id;
	
	//TODO: TEMPORARY FIX!!! Once Order mapping is integrated, switch back and away from String
//	@OneToOne @JoinColumn(name = "order_id")
	@Column
	private String orderId;
	
	@OneToOne @JoinColumn(name = "driver_id")
	private Driver driver;
	
	@Column(name = "start_time")
	private Time startTime;
	
	@Column(name = "pickup_time")
	private Time pickupTime;
	
	@Column(name = "deliver_time")
	private Time deliverTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Time pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Time getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Time deliverTime) {
		this.deliverTime = deliverTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliverTime == null) ? 0 : deliverTime.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((pickupTime == null) ? 0 : pickupTime.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		if (deliverTime == null) {
			if (other.deliverTime != null)
				return false;
		} else if (!deliverTime.equals(other.deliverTime))
			return false;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (pickupTime == null) {
			if (other.pickupTime != null)
				return false;
		} else if (!pickupTime.equals(other.pickupTime))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", orderId=" + orderId + ", driverId=" + driver + ", startTime=" + startTime
				+ ", pickupTime=" + pickupTime + ", deliverTime=" + deliverTime + "]";
	}
	
}
