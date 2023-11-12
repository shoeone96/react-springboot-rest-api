package com.programmers.coffeespring.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {

    private Long customerId;
    private String customerName;
    private String username;
    private String password;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Customer fromDbMapper(Long customerId, String customerName, String username, String password, String phoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Customer(customerId, customerName, username, password, phoneNumber, createdAt, updatedAt);
    }
}
