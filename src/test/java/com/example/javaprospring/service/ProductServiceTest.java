package com.example.javaprospring.service;

import com.example.javaprospring.model.Product;
import com.example.javaprospring.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private static ProductRepository productRepository;
    private static ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Test
    void saveOk() {
        Product product = Product.builder()
                .id(1)
                .name("bag")
                .cost(80)
                .orderId(2)
                .build();
        Mockito.when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.save(product);
        assertEquals(product, savedProduct);
    }
}
