package com.tmf.store.entites;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderId;

//	@Column(name = "status")
//	@ColumnDefault("'ordered'")
//	private String status;
	
	@ManyToOne
	@JoinColumn(name="address_id", referencedColumnName = "address_id")
	private Address address;

	@Column(name = "ordered_on")
	private LocalDateTime orderedOn;

	@PrePersist
	protected void onCreate() {
		if (orderedOn == null)
			orderedOn = LocalDateTime.now();
	}

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	

	public Order() {
		super();
	}
	
	

	public Order(Address address,User user) {
		super();
		this.address = address;
		this.user = user;
	}



	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public LocalDateTime getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(LocalDateTime orderedOn) {
		this.orderedOn = orderedOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
