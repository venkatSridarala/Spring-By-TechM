package com.tmf.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmf.store.entites.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
