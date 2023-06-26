package com.example.javaprospring.service;

import com.example.javaprospring.model.Order;
import com.example.javaprospring.model.Product;
import com.example.javaprospring.repository.OrderRepository;
import com.example.javaprospring.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public List<Order>  getAll() {
        List<Order> all = (List<Order>) orderRepository.findAll();
        for (Order order : all) {
            List<Product> productsByOrderId =
                    productService.getProductsByOrderId(order.getId());
            order.setProducts(productsByOrderId);
            order.setCost(productService.getTotalCost(order.getId()));
        }
        return all;
    }

    public Order save() {
        Order order = new Order();
        order.setDate(LocalDate.now());
        return orderRepository.save(order);
    }

    public Order addProductToOrder(int orderId, int productId) {
        Order order = getOrderById(orderId);
        Product product = productService.getProductById(productId);
        product.setOrderId(orderId);
        productRepository.save(product);
        order.setProducts(productService.getProductsByOrderId(orderId));
        return order;
    }
}
