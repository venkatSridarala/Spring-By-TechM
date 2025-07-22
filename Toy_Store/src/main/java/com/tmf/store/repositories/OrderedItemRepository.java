package com.tmf.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tmf.store.entites.OrderedItem;
import com.tmf.store.utils.UserOrderItem;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {

	@Query(value = "SELECT oi.ordered_item_id AS orderedItemId, od.order_id AS orderId, u.user_id AS userId, "
			+ "CONCAT(u.first_name, ' ', u.last_name) AS fullName, id.item_name AS itemName, id.price, "
			+ "oi.quantity, od.ordered_on AS orderedOn, oi.status " + "FROM order_details od "
			+ "JOIN ordered_items oi ON od.order_id = oi.order_id " + "JOIN item_details id ON id.item_id = oi.item_id "
			+ "JOIN user_details u ON u.user_id = od.user_id " + "WHERE u.user_id = :userId "
			+ "ORDER BY od.ordered_on DESC", nativeQuery = true)
	List<UserOrderItem> getOrders(@Param("userId") Long userId);
	
	@Query(value = "SELECT oi.ordered_item_id AS orderedItemId, od.order_id AS orderId, u.user_id AS userId, "
			+ "CONCAT(u.first_name, ' ', u.last_name) AS fullName, id.item_name AS itemName, id.price, "
			+ "oi.quantity, od.ordered_on AS orderedOn, oi.status " + "FROM order_details od "
			+ "JOIN ordered_items oi ON od.order_id = oi.order_id " + "JOIN item_details id ON id.item_id = oi.item_id "
			+ "JOIN user_details u ON u.user_id = od.user_id "
			+ "ORDER BY od.ordered_on DESC", nativeQuery = true)
	List<UserOrderItem> getAllOrders();

}
