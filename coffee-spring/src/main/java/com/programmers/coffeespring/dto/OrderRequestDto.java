package com.programmers.coffeespring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderRequestDto {
    @NonNull List<OrderItems> orderItems;
    Long voucherId;
    long totalPrice;
}
