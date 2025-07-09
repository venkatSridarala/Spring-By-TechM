package com.example.Supplement_Store.services;
import com.example.Supplement_Store.entities.User;
import com.example.Supplement_Store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	@Service
	public class UserService {
	    
	    @Autowired
	    private UserRepository userRepository;

	    public boolean registerUser(User user) {
	        if (userRepository.findByEmail(user.getEmail()) == null) {
	            userRepository.save(user);
	            return true;
	        }
	        return false;
	    }

	    public User login(String email, String password) {
	        return userRepository.findByEmailAndPassword(email, password)
	                             .stream()
	                             .findFirst()
	                             .orElse(null);
	    }
	}
