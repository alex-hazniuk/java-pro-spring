package com.example.javaprospring.controller;

import com.example.javaprospring.model.Order;
import com.example.javaprospring.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAll();
    }

    @PostMapping("/products/{productId}")
    public Order create(@PathVariable Integer productId) {
        return orderService.save(productId);
    }

    @PutMapping("/{orderId}/products/{productId}")
    public Order update(@PathVariable Integer orderId,
                        @PathVariable Integer productId) {
        return orderService.addProductToOrder(orderId, productId);
    }
}
