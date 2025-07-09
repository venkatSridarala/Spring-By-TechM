package com.example.Supplement_Store.controllers;

import com.example.Supplement_Store.entities.Product;
import com.example.Supplement_Store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showCatalog(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "catalog"; // display all products
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
}