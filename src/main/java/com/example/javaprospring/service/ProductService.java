package com.example.javaprospring.service;

import com.example.javaprospring.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final List<Integer> ids = new ArrayList<>();

    public Product save(Product product) {
        int id = ids.size() + 1;
        ids.add(id);
        product.setId(id);
        products.add(product);
        return product;
    }

    public Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }
}
