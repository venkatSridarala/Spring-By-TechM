package com.tmf.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmf.store.entites.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
