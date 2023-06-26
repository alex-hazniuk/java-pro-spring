package com.example.javaprospring.service;

import com.example.javaprospring.model.Product;
import com.example.javaprospring.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public List<Product> getProductsByOrderId(int orderId) {
        return productRepository.getProductsByOrderId(orderId);
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public double getTotalCost(int orderId) {
        return productRepository.getTotalCostByOrderId(orderId);
    }
}
