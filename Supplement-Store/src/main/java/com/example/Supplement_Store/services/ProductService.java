package com.example.Supplement_Store.services;

import com.example.Supplement_Store.entities.Product;
import com.example.Supplement_Store.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public void saveProduct(Product product) {
        if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
            product.setImageUrl("/images/protein.jpg"); 
        }
        productRepository.save(product);
    }

}