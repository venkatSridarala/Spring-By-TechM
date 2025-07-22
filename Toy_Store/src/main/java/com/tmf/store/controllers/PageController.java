package com.tmf.store.controllers;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tmf.store.entites.Item;
import com.tmf.store.utils.DbConnection;

@Controller
public class PageController {
	
	@GetMapping("/cart")
	public String getCartPage(Model m) {
		List<Item> items = new ArrayList<>();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from item_details";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("item_id"));
				item.setAvailableQuantity(rs.getInt("avail_quantity"));
				item.setDescription(rs.getString("description"));
				item.setAvailable(rs.getBoolean("is_avail"));
				item.setItemName(rs.getString("item_name"));
				item.setCategory(rs.getString("category"));
				item.setPrice(rs.getDouble("price"));
				item.setWeight(rs.getDouble("weight"));
				items.add(item);
			}
			ps.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("items", items);
		return "Cart";
	}
}
