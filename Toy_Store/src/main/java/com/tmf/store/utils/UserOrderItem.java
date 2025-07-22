package com.tmf.store.utils;

import java.time.LocalDateTime;

//JPA interface projection
public interface UserOrderItem {
	
	Long getOrderedItemId();

	Long getOrderId();

	Long getUserId(); // these must be the aliases name of the sql query

	String getFullName();

	String getItemName();

	double getPrice();

	int getQuantity();

	String getStatus();

	LocalDateTime getOrderedOn();
}
