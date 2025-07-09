package com.example.Supplement_Store.controllers;

import com.example.Supplement_Store.entities.CartItem;
import com.example.Supplement_Store.entities.Product;
import com.example.Supplement_Store.entities.User;
import com.example.Supplement_Store.repositories.CartItemRepository;
import com.example.Supplement_Store.repositories.ProductRepository;
import com.example.Supplement_Store.repositories.UserRepository;
import com.example.Supplement_Store.services.CartService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartItemRepository cartItemRepository;


    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return "redirect:/products";
        }

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(1); 

        cartService.addToCart(item);
        return "redirect:/cart/view";
    }
    
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam Long userId) {

        Product product = productRepository.findById(productId).orElse(null);
        User user = userRepository.findById(userId).orElse(null); 

        if (product != null && user != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(1); 
            cartItemRepository.save(cartItem);
        }

        return "redirect:/cart?userId=" + userId;
    }
    

    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("cartItems", cartService.getCartItems(user));
        return "cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return "redirect:/cart/view";
    }
}
