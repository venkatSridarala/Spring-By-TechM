package com.tmf.store.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmf.store.entites.Address;
import com.tmf.store.entites.User;
import com.tmf.store.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User authUser(String email,String password) {
		List<User> userList = userRepo.findByEmailAndPassword(email, password);
		if(userList.size() == 1) {
			return userList.get(0);
		}
		return null;
	}
	
	@Transactional
	public List<Address> getAddressListByUser(User user){
		if(user != null) {
			User userForAddress = userRepo.findById(user.getId()).orElse(null);
			if(userForAddress != null) {				
				Hibernate.initialize(userForAddress.getAddressList());
				return userForAddress.getAddressList();
			}
		}
		return null;
	}
	
	public void updateUserAndAddress(List<String> doorNos, List<String> states, List<Long> pinCodes, List<String> cities,
			List<String> areas, List<Long> addressIds,User user,String firstName,String lastName,String email,String phno) {
		if(user == null) return;
	
		List<Address> addresses = new ArrayList<>();
		for (int i = 0; i < addressIds.size(); i++) {
			Address address = new Address(doorNos.get(i), areas.get(i), cities.get(i), states.get(i), pinCodes.get(i), user);
			address.setAddressId(addressIds.get(i));
			addresses.add(address);
		}
		user.setAddressList(addresses);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhno(phno);
		userRepo.save(user);
	}
	
	@Transactional
	public void updateUser(String firstName,String lastName,String email,String phno,User user) {
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhno(phno);
		
		userRepo.save(user);
	}
	
	public void addUser(String firstName,String lastName,String email,String phno,String password,String doorNo,String area,String city,String state,Long pinCode) {
		User newUser = new User(firstName, lastName, phno, email, password);
		Address newAddress = new Address(doorNo, area, city, state, pinCode, newUser);
		List<Address> addressList = Arrays.asList(newAddress);
		newUser.setAddressList(addressList);
		userRepo.save(newUser);
	}
}
