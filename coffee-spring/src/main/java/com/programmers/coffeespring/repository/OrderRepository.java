package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Order;

public interface OrderRepository {

    void insert(Order order);

    void insertWithVoucher(Order order);

}
