package com.tmf.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmf.store.entites.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

	@Modifying
	@Transactional
	@Query(value = "update item_details set is_avail = NOT is_avail where item_id = :id",nativeQuery = true)
	int updateIsAvailable(@Param("id") long id);
	
	List<Item> findByIsAvailable(Boolean isAvail);
}
