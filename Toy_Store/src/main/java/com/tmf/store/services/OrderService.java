package com.tmf.store.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmf.store.entites.Address;
import com.tmf.store.entites.Item;
import com.tmf.store.entites.Order;
import com.tmf.store.entites.OrderedItem;
import com.tmf.store.entites.User;
import com.tmf.store.repositories.AddressRepository;
import com.tmf.store.repositories.ItemRepository;
import com.tmf.store.repositories.OrderRepository;
import com.tmf.store.repositories.OrderedItemRepository;
import com.tmf.store.utils.CartDetails;
import com.tmf.store.utils.CartItem;
import com.tmf.store.utils.UserOrderItem;

@Service
public class OrderService {

	@Autowired
	private OrderedItemRepository orderItemRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private OrderRepository orderRepo;

	public List<UserOrderItem> getAllOrdersOfUser(Long userId) {
		return orderItemRepo.getOrders(userId);
	}

	public List<UserOrderItem> getAllOrders() {
		return orderItemRepo.getAllOrders();
	}

	@Transactional
	public String cancelItem(Long orderItemId) {
		OrderedItem orderItem = orderItemRepo.findById(orderItemId).orElse(null);
		if (orderItem == null)
			return "fail";

		if (orderItem.getStatus().equals("ordered")) {
			orderItem.setStatus("cancelled");
			Item item = orderItem.getItem();
			item.setAvailableQuantity(item.getAvailableQuantity() + orderItem.getQuantity());
			orderItemRepo.save(orderItem);
			return "success";
		}
		return "fail";
	}

	@Transactional
	public String updateItemStatus(Long id, String status) {
		OrderedItem orderItem = orderItemRepo.findById(id).orElse(null);
		if (orderItem == null)
			return "fail";

		if (orderItem.getStatus().equals("ordered")) {
			if("cancelled".equals(status)) {
				Item item = orderItem.getItem();
				item.setAvailableQuantity(item.getAvailableQuantity() + orderItem.getQuantity());
			}
			orderItem.setStatus(status);
			orderItemRepo.save(orderItem);
			return "success";
		}
		return "fail";
	}

	@Transactional
	public void createOrder(User user, CartDetails cart) {
		Address address = addressRepo.findById(cart.getAddressId()).orElse(null);
		if (address == null)
			return;
		Order order = new Order(address, user);
		orderRepo.save(order);

		List<CartItem> items = cart.getItems();
		List<OrderedItem> orderItems = new ArrayList<>();

		List<Long> itemIds = items.stream().map(item -> item.getItemId()).collect(Collectors.toList());
		Map<Long,Item> itemsMap = itemRepo.findAllById(itemIds).stream().collect(Collectors.toMap(item -> item.getItemId(), item -> item));
		
		items.forEach((item) -> {
			Item fullItem = itemsMap.get(item.getItemId());
			if(fullItem != null) {
				if(fullItem.getAvailableQuantity() >= item.getQuantity()) {
					fullItem.setAvailableQuantity(fullItem.getAvailableQuantity() - item.getQuantity());
					orderItems.add(new OrderedItem(item.getQuantity(), order, fullItem));
				}else {
					throw new RuntimeException("Insufficient stock!!!");
				}
			}
		});

		orderItemRepo.saveAll(orderItems);
//		items.stream().forEach((item) -> {
//			Item totalItem = itemRepo.findById(item.getItemId()).orElse(null);
//			if(totalItem != null) orderItems.add(new OrderedItem(item.getQuantity(), order, totalItem));
//		});
	}
}
