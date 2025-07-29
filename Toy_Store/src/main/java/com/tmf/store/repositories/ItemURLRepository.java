package com.tmf.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmf.store.entites.ItemURL;

@Repository
public interface ItemURLRepository extends JpaRepository<ItemURL, Long>{

}
