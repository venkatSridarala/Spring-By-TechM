package com.example.Supplement_Store.controllers;

import com.example.Supplement_Store.entities.Order;
import com.example.Supplement_Store.entities.Product;
import com.example.Supplement_Store.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Product> products = adminService.getAllProducts();
        List<Order> orders = adminService.getAllOrders();
        model.addAttribute("products", products);
        model.addAttribute("orders", orders);
        return "admin_dashboard";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Long id) {
        adminService.deleteProduct(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/updateOrderStatus")
    public String updateOrderStatus(@RequestParam Long id, @RequestParam String status) {
        adminService.changeOrderStatus(id, status);
        return "redirect:/admin/dashboard";
    }
}