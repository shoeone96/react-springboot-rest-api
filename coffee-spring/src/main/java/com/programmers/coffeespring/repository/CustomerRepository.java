package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
