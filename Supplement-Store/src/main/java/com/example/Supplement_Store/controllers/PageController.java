package com.example.Supplement_Store.controllers;

import com.example.Supplement_Store.repositories.ProductRepository;
import com.example.Supplement_Store.repositories.CartItemRepository;
import com.example.Supplement_Store.entities.CartItem;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "home";
    }

    
    @GetMapping("/product-list")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products"; 
    }


    @GetMapping("/cart")
    public String viewCart(@RequestParam Long userId, Model model) {
        List<CartItem> items = cartItemRepository.findByUserId(userId);
        model.addAttribute("cartItems", items);
        return "cart";
    }

}
