package com.example.javaprospring.service;

import com.example.javaprospring.model.Product;
import com.example.javaprospring.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
