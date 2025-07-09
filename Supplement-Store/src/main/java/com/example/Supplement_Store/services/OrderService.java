package com.example.Supplement_Store.services;

import com.example.Supplement_Store.entities.*;
import com.example.Supplement_Store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Order placeOrder(User user, Address address) {
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);
        Order order = new Order(user, savedAddress, new Date(), "Placed");
        return orderRepository.save(order);
    }
}
