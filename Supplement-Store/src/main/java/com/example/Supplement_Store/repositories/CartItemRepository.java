package com.example.Supplement_Store.repositories;

import com.example.Supplement_Store.entities.CartItem;
import com.example.Supplement_Store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    
    List<CartItem> findByUser(User user);

    List<CartItem> findByUserId(Long userId);
}
