package com.tmf.store.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address_details")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private long addressId;

	@Column(name = "door_no", nullable = false)
	private String doorNo;

	@Column(name = "area", nullable = false)
	private String area;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "pincode", nullable = false)
	private long pinCode;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public Address() {
		super();
	}

	public Address(String doorNo, String area, String city, String state, long pinCode,User user) {
		this.doorNo = doorNo;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.user = user;
	}

	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getPinCode() {
		return pinCode;
	}

	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}

}
