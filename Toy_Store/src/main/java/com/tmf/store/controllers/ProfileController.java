package com.tmf.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmf.store.entites.Address;
import com.tmf.store.entites.User;
import com.tmf.store.services.AddressService;
import com.tmf.store.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;

	@GetMapping
	public String getProfileDetails(HttpSession session, Model m) {
		User user = (User) session.getAttribute("user");
		List<Address> addressList = userService.getAddressListByUser(user);
		if (addressList != null) {
			m.addAttribute("addresses", addressList);
			return "Profile";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/admin")
	public String getAdminProfileDetails(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:/login";
		return "AdminProfile";
	}
	
	@PostMapping("/admin")
	public String updateAdminProfileDetails(@RequestParam("phno") String phno
											,@RequestParam("email") String email
											,@RequestParam("lastname") String lastName
											,@RequestParam("firstname") String firstName,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user == null) return "redirect:/login";
		userService.updateUser(firstName,lastName,email,phno,user);
		
		return "redirect:/profile/admin";		
	}
	
	@PostMapping
	public String updateProfileDetails(@RequestParam("phno") String phno
										,@RequestParam("email") String email
										,@RequestParam("lastname") String lastName
										,@RequestParam("firstname") String firstName
										,@RequestParam("state") List<String> states
										,@RequestParam("pincode") List<Long> pinCode
										,@RequestParam("city") List<String> city
										,@RequestParam("area") List<String> area
										,@RequestParam("doorno") List<String> doorNo
										,@RequestParam("addressId") List<Long> addressId
										,HttpSession session) {
		User user = (User) session.getAttribute("user");
		//addressService.updateAddresses(doorNo, states, pinCode, city, area, addressId, user);
		if(user == null) return "redirect:/login";
		userService.updateUserAndAddress(doorNo, states, pinCode, city, area, addressId, user, firstName, lastName, email, phno);
		return "redirect:/profile";
	}
	
	@PostMapping("/address")
	public String addAddress(HttpSession session,@RequestParam("state") String state, @RequestParam("pincode") long pinCode,
			@RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("doorno") String door) {
		User user = (User) session.getAttribute("user"); 
		if(user == null) return "redirect:/login";
		
		Address address = new Address(door, area, city, state, pinCode,user);
		addressService.insertAddress(address);
		return "redirect:/profile";
	}
}
