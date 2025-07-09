package com.example.Supplement_Store.controllers;

import com.example.Supplement_Store.entities.User;
import com.example.Supplement_Store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email already registered");
            return "signup";
        }

        User newUser = new User(username, email, password, "USER");
        userRepository.save(newUser);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              Model model) {

        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);

        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        User user = userOptional.get();

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/home";
        }
    }
}
