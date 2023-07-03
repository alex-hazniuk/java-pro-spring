package com.example.javaprospring.service;

import com.example.javaprospring.model.Order;
import com.example.javaprospring.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private static OrderRepository orderRepository;
    private static OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void get_orderById_ok() {
        int orderId = 1;
        Order order = Order.builder()
                .id(orderId)
                .date(LocalDate.now())
                .build();
        Mockito.when(orderRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(order));
        int expected = 1;
        int actual = orderService.getOrderById(orderId).getId();
        assertEquals(expected, actual);
    }

    @Test
    void get_orderById_notOk() {
        int orderId = 1;
        Mockito.when(orderRepository.findById(ArgumentMatchers.anyInt()))
                .thenThrow(ResponseStatusException.class);
        assertThrows(ResponseStatusException.class, () -> {
            orderService.getOrderById(orderId);
        });
    }

    @Test
    void getAll_ok() {
        List<Order> orders = List.of(
                Order.builder().id(1).date(LocalDate.now()).build(),
                Order.builder().id(2).date(LocalDate.now()).build(),
                Order.builder().id(3).date(LocalDate.now()).build()
        );
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        List<Order> all = orderService.getAll();
        int expected = 3;
        int actual = all.size();
        assertEquals(expected, actual);
        assertTrue(all.contains(
                Order.builder()
                        .id(2)
                        .date(LocalDate.now())
                        .build())
        );
    }

    @Test
    void save_ok() {
        Order order = Order.builder()
                .id(1)
                .date(LocalDate.now())
                .build();
        Mockito.when(orderRepository.save(ArgumentMatchers.any())).thenReturn(order);
        Order savedOrder = orderService.save();
        assertEquals(order, savedOrder);
    }
}
