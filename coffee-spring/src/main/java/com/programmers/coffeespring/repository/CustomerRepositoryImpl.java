package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Customer;
import com.programmers.coffeespring.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from customers where phone_number = :phoneNumber",
                Collections.singletonMap("phoneNumber", phoneNumber),
                JdbcUtils.customerMapper
        ));
    }
}
