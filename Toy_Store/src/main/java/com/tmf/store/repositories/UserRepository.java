package com.tmf.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmf.store.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByEmailAndPassword(String email,String password);
}
