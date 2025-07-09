package com.example.Supplement_Store.repositories;

import com.example.Supplement_Store.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> 
{
	
}
