package com.example.Supplement_Store.controllers;

import com.example.Supplement_Store.entities.*;
import com.example.Supplement_Store.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/checkout")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showCheckoutForm() {
        return "checkout";
    }

    @PostMapping
    public String placeOrder(@RequestParam String street,
                              @RequestParam String city,
                              @RequestParam String state,
                              @RequestParam String zip,
                              HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Address address = new Address(street, city, state, zip, user);
            Order order = orderService.placeOrder(user, address);
            model.addAttribute("orderId", order.getId());
            return "order_tracking";
        }
        return "redirect:/login";
    }
}