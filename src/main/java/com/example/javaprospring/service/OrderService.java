package com.example.javaprospring.service;

import com.example.javaprospring.model.Order;
import com.example.javaprospring.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {
    private final Map<Integer, Order> ordersContainer = new HashMap<>();
    private final List<Integer> ids = new ArrayList<>();
    private final ProductService productService;

    public OrderService(ProductService productService) {
        this.productService = productService;
    }

    public Order getOrderById(int id) {
        return ordersContainer.entrySet().stream()
                .filter(e -> e.getKey() == id)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow();
    }

    public List<Order> getAll() {
        return ordersContainer.values().stream().toList();
    }

    public Order save(int productId) {
        List<Product> productsForOrder = new ArrayList<>();
        Product product = get(productId);
        productsForOrder.add(product);
        int orderId = ids.size() + 1;
        ids.add(orderId);
        Order order = Order.builder()
                .id(orderId)
                .date(LocalDate.now())
                .cost(product.getCost())
                .products(productsForOrder).build();
        ordersContainer.put(orderId, order);
        return ordersContainer.get(orderId);
    }

    public Order addProductToOrder(int orderId, int productId) {
        Order order = getOrderById(orderId);
        Product product = get(productId);
        order.getProducts().add(product);
        double costSum = order.getProducts().stream()
                .mapToDouble(Product::getCost)
                .sum();
        order.setCost(costSum);
        return ordersContainer.get(orderId);
    }

    private Product get(int id) {
        return productService
                .getProductById(id)
                .orElseThrow();
    }
}
