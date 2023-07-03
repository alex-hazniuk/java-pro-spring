package com.example.javaprospring.repository;

import com.example.javaprospring.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAll();
}
