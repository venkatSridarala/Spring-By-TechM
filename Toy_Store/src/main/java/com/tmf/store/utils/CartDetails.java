package com.tmf.store.utils;

import java.util.List;

public class CartDetails {
	private List<CartItem> items;
	private long addressId;
	
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	
}
