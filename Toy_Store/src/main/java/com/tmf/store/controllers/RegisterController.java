package com.tmf.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tmf.store.services.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userServie;
	
	@RequestMapping
	public String getRegisterPage() {
		return "Register";
	}
	
	@PostMapping
	public String registerUser(@RequestParam("pinCode") Long pinCode
								,@RequestParam("state") String state
								,@RequestParam("city") String city
								,@RequestParam("area") String area
								,@RequestParam("doorNo") String doorNo
								,@RequestParam("password") String password
								,@RequestParam("phone") String phone
								,@RequestParam("email") String email
								,@RequestParam("lastname") String lastName
								,@RequestParam("firstname") String firstName) {
		userServie.addUser(firstName, lastName, email, phone, password, doorNo, area, city, state, pinCode);
		return "redirect:/login";
	}
	
}
