package com.programmers.coffeespring.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Customer {

    private Long customerId;
    private String customerName;
    private String username;
    private String password;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
