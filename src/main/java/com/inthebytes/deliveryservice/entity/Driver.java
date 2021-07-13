package com.inthebytes.deliveryservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "driver")
public class Driver implements Serializable {
	
	public static final Integer STATUS_OFFLINE = 0;
	public static final Integer STATUS_IDLE = 1;
	public static final Integer STATUS_PICKING_UP = 2;
	public static final Integer STATUS_DELIVERING = 3;
	
	private static final long serialVersionUID = -8571920787667282122L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	    name = "UUID",
	    strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "driver_id")
	private String id;
	
	@OneToOne @JoinColumn(name = "user_id")
	private String userId;
	
	@Column(name = "vehicle_id")
	private String vehicleId;
	
	@Column(name = "financial_id")
	private String financialId;
	
	@Column(name = "status")
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getFinancialId() {
		return financialId;
	}

	public void setFinancialId(String financialId) {
		this.financialId = financialId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((financialId == null) ? 0 : financialId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((vehicleId == null) ? 0 : vehicleId.hashCode());
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
		Driver other = (Driver) obj;
		if (financialId == null) {
			if (other.financialId != null)
				return false;
		} else if (!financialId.equals(other.financialId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (vehicleId == null) {
			if (other.vehicleId != null)
				return false;
		} else if (!vehicleId.equals(other.vehicleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", userId=" + userId + ", vehicleId=" + vehicleId + ", financialId=" + financialId
				+ ", status=" + status + "]";
	}
	
	
}
