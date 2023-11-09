package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository {

    List<Product> findAll();

    void save(Product product);
    void clear();
}
