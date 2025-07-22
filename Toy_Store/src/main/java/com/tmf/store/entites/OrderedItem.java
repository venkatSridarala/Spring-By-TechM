package com.tmf.store.entites;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordered_items")
public class OrderedItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordered_item_id")
	private long orderedItemId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "status")
	@ColumnDefault("'ordered'")
	private String status;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "item_id", referencedColumnName = "item_id")
	private Item item;
	
	public OrderedItem() {
		super();
	}
	
	

	public OrderedItem(int quantity, Order order, Item item) {
		super();
		this.quantity = quantity;
		this.order = order;
		this.item = item;
		this.status = "ordered";
	}



	public long getOrderedItemId() {
		return orderedItemId;
	}

	public void setOrderedItemId(long orderedItemId) {
		this.orderedItemId = orderedItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
