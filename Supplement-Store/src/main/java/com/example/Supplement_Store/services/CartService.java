package com.example.Supplement_Store.services;

import com.example.Supplement_Store.entities.CartItem;
import com.example.Supplement_Store.entities.User;
import com.example.Supplement_Store.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addToCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}
