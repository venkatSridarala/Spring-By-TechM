package com.tmf.store.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmf.store.entites.Address;
import com.tmf.store.entites.User;
import com.tmf.store.services.OrderService;
import com.tmf.store.services.UserService;
import com.tmf.store.utils.CartDetails;
import com.tmf.store.utils.CartItem;
import com.tmf.store.utils.UserOrderItem;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getAllOrders(HttpSession session,Model m) {
		User user = (User)session.getAttribute("user");
		if(user != null) {
			List<UserOrderItem> orders = orderService.getAllOrdersOfUser(user.getId());
			m.addAttribute("orders",orders);
//			System.out.println(orders.size());
		}
		return "Orders";
	}
	
	@GetMapping("/admin")
	public String getOrdersAdmin(HttpSession session,Model m) {
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:/login";
		List<UserOrderItem> orders = orderService.getAllOrders();
		m.addAttribute("orders",orders);
		return "AdminOrders";
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<String> updateOrder(@PathVariable("id") Long id) {
		String status = orderService.cancelItem(id);
		if(status.equals("success")) return ResponseEntity.ok("Item Cancelled");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(status);
	}
	
	@PutMapping("/admin")
	@ResponseBody
	public ResponseEntity<String> updateOrderStatusAdmin(@RequestParam("id") Long id,@RequestParam("status") String status){
		String statusResponse = orderService.updateItemStatus(id, status);
		if(statusResponse.equals("success")) return ResponseEntity.ok("Item Status Updated");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(statusResponse);
	}
	
	@GetMapping("/address")
	public String showAddressListOfUser(HttpSession session,Model m) {
		User user = (User) session.getAttribute("user");
		Optional<User> checkUser = Optional.ofNullable(user);
		if(checkUser.isEmpty()) return "redirect:/login";
		
		List<Address> addressList = userService.getAddressListByUser(user);
		m.addAttribute("addresses",addressList);
		return "CartAddress";
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<String> createOrder(HttpSession session,@RequestBody CartDetails cart) {
		List<CartItem> cartItems = cart.getItems();
		for(CartItem item:cartItems) {
			System.out.println(item.getItemId() + "-" + item.getQuantity());	
		}
		System.out.println("Size: " + cartItems.size());
		User user = (User) session.getAttribute("user");
		System.out.println(user.getFirstName());
		System.out.println("Address Id: " + cart.getAddressId());
		
		orderService.createOrder(user, cart);
	
		return ResponseEntity.ok("Order received successfully");
	}
}
