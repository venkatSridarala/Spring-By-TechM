package com.tmf.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmf.store.entites.Item;
import com.tmf.store.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepo;
	
	public List<Item> getAllItems(){
		return itemRepo.findByIsAvailable(true);
	}
	
	public List<Item> getAllItemsAdmin(){
		return itemRepo.findAll();
	}
	
	public int updateIsAvailable(long id) {
		return itemRepo.updateIsAvailable(id);
	}
	
	public Item getItemById(Long id){
		return itemRepo.findById(id).orElse(null);
	}
}
