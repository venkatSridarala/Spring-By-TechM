package com.tmf.store.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.tmf.store.entites.Item;
import com.tmf.store.entites.User;
import com.tmf.store.services.ItemService;
import com.tmf.store.services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ItemService itemService;
    
    // Show login page
    @GetMapping({"", "/"})
    public String getLoginPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/items";
        }
        return "login";
    }
    
    // Handle GET on /login/auth → redirect back to login
    @GetMapping("/auth")
    public String authGetFallback() {
        return "redirect:/login";
    }
    
    // Handle user login (POST)
    @PostMapping("/auth")
    public String authenticateUser(
            Model m,
            HttpSession session,
            @RequestParam String email,
            @RequestParam String password) {
        
        User user = userService.authUser(email, password);
        if (user == null || "admin".equalsIgnoreCase(user.getRole())) {
            m.addAttribute("error", "Incorrect email or password");
            return "login";
        }
        
        session.setAttribute("user", user);
        return "redirect:/items";
    }
    
    // Handle GET on /login/auth/admin → redirect back to login
    @GetMapping("/auth/admin")
    public String authAdminGetFallback() {
        return "redirect:/login";
    }
    
    // Handle admin login (POST)
    @PostMapping("/auth/admin")
    public String authenticateAdmin(
            Model m,
            HttpSession session,
            @RequestParam String email,
            @RequestParam String password) {
        
        User user = userService.authUser(email, password);
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            m.addAttribute("error", "Incorrect email or password");
            return "login";
        }
        
        session.setAttribute("user", user);
        return "redirect:/items/admin";
    }
    
    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
    
    // Display customer items page
    @GetMapping("/items")
    public String showItems(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"customer".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        
        List<Item> items = itemService.getAllItems();
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        model.addAttribute("jsonItems", new Gson().toJson(items));
        return "items";
    }
    
    // Display admin items page
    @GetMapping("/items/admin")
    public String showAdminItems(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
            return "redirect:/login";
        }
        
        List<Item> items = itemService.getAllItems();
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        model.addAttribute("jsonItems", new Gson().toJson(items));
        return "admin-items";
    }
}
