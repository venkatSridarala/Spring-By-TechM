package com.tmf.store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmf.store.entites.Address;
import com.tmf.store.entites.User;
import com.tmf.store.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepo;

	public void insertAddress(Address address) {
		addressRepo.save(address);
	}

//	public void updateAddresses(List<String> doorNos, List<String> states, List<Long> pinCodes, List<String> cities,
//			List<String> areas, List<Long> addressIds, User user) {
//		List<Address> addresses = new ArrayList<>();
//		for (int i = 0; i < addressIds.size(); i++) {
//			Address address = new Address(doorNos.get(i), areas.get(i), cities.get(i), states.get(i), pinCodes.get(i), user);
//			address.setAddressId(addressIds.get(i));
//			addresses.add(address);
//		}
//		List<Address> validAddresses = new ArrayList<>();
//		for (Address address : addresses) {
//			if (addressRepo.existsById(address.getAddressId())) {
//				validAddresses.add(address);
//			} else {
//				//System.out.println(address.getAddressId());
//				System.out.println("No such address");
//			}
//		}
//		List<Address> updatedAddresses = addressRepo.saveAll(validAddresses);
//	}
}
