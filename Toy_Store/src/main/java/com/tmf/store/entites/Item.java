package com.tmf.store.entites;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_details")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long itemId;

	@Column(name = "item_name", nullable = false)
	private String itemName;

	@Column(name = "price", nullable = false)
	private double price;

	@Column(name = "weight")
	private double weight;

	@Column(name = "category")
	private String category;

	@Column(name = "description")
	private String description;

	@Column(name = "is_avail")
	private boolean isAvailable;

	@Column(name = "avail_quantity")
	private int availableQuantity;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private List<ItemURL> itemUrlList;
	
	public Item() {
		super();
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ItemURL> getItemUrlList() {
		return itemUrlList;
	}

	public void setItemUrlList(List<ItemURL> itemUrlList) {
		this.itemUrlList = itemUrlList;
	}
	
	
}
