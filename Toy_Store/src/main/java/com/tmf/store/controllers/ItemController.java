package com.tmf.store.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tmf.store.entites.Item;
import com.tmf.store.entites.User;
import com.tmf.store.services.ItemService;
import com.tmf.store.utils.DbConnection;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public String getItemsPage(Model m) {

		List<Item> items = itemService.getAllItems();
		m.addAttribute("items", items);
		Gson gson = new Gson();
		String listItemsJson = gson.toJson(items);
		m.addAttribute("jsonItems", listItemsJson);

		return "Items";
	}
	
	@GetMapping("/admin")
	public String getAdminItems(Model m,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:/login";
		
		List<Item> items = itemService.getAllItemsAdmin();
		m.addAttribute("items", items);
		
		return "AdminItems";
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<String> updateIsAvailableItem(@PathVariable("id") long id){
		int result = itemService.updateIsAvailable(id);
		if(result == 1)
			return ResponseEntity.ok("updated successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such item to be updated");
	}
	
	@GetMapping("/{id}")
	public String getItemById(@PathVariable("id") Long id,HttpSession session,Model m) {
		User user = (User) session.getAttribute("user");
		Optional<User> checkUser = Optional.ofNullable(user);
		if(checkUser.isEmpty()) return "redirect:/login";
		Item item = itemService.getItemById(id);
		if(item == null) return ""; // To complete
		
		m.addAttribute("item",item);
		return "Item";
	}

	@GetMapping("/admin/add")
	public String addItem(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:/login";
		return "AddItem";
	}

	@PostMapping("/admin/add")
	public String postItem(@RequestParam("name") String name,
	                       @RequestParam("price") double price,
	                       @RequestParam("weight") double weight,
	                       @RequestParam("category") String category,
	                       @RequestParam("description") String description,
	                       @RequestParam("quantity") int quantity,
	                       @RequestParam("imageUrls") List<String> imageURLs
	                       ,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:/login";
	    Connection conn = null;
	    try {
	        conn = DbConnection.getConnection();
	        conn.setAutoCommit(false);

	        String insertItemQuery = "INSERT INTO item_details(avail_quantity, description, item_name, price, weight, category, is_avail) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(insertItemQuery, Statement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, quantity);
	        ps.setString(2, description);
	        ps.setString(3, name);
	        ps.setDouble(4, price);
	        ps.setDouble(5, weight);
	        ps.setString(6, category);
	        ps.setBoolean(7, true);

	        int res = ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        int id = -1;
	        if (rs.next()) {
	            id = rs.getInt(1); 
	        }

	        String insertUrlsQuery = "INSERT INTO item_url(url, item_id) VALUES (?, ?)";
	        PreparedStatement ps1 = conn.prepareStatement(insertUrlsQuery);
	        for (String url : imageURLs) {
	            ps1.setString(1, url);
	            ps1.setInt(2, id);
	            ps1.addBatch();
	        }

	        ps1.executeBatch();
	        conn.commit();

	        ps.close();
	        ps1.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        if (conn != null) {
	            try {
	                conn.rollback();
	                System.out.println("Rolled back successfully");
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        }
	    }
	    return "login";
	}

}
