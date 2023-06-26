package com.example.javaprospring.service;

import com.example.javaprospring.model.Order;
import com.example.javaprospring.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order save() {
        Order order = new Order();
        order.setDate(LocalDate.now());
        return orderRepository.save(order);
    }
}
