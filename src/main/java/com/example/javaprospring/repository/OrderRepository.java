package com.example.javaprospring.repository;

import com.example.javaprospring.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
