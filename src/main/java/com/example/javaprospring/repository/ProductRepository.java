package com.example.javaprospring.repository;

import com.example.javaprospring.model.Product;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> getProductsByOrderId(int orderId);

    @Query(
            """
                    SELECT SUM(cost)
                    FROM products
                    WHERE order_id = ?1
                    """
    )
    double getTotalCostByOrderId(int orderId);
}
