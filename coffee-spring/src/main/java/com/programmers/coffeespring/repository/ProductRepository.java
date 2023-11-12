package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public interface ProductRepository {

    List<Product> findAll();

    void save(Product product);
    void clear();

    Optional<Product> findById(long productId);

    List<Product> findAllByCategory(Category category);
}
