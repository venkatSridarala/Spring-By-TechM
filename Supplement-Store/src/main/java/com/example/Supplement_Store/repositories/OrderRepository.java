package com.example.Supplement_Store.repositories;

import com.example.Supplement_Store.entities.Order;
import com.example.Supplement_Store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
