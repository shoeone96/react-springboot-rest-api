package com.programmers.coffeespring.model;

import com.programmers.coffeespring.dto.OrderItems;
import com.programmers.coffeespring.dto.OrderRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {

    private long orderId;
    private long voucherId;
    private long totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItems> orderItemList;

    public Order(long voucherId, long totalPrice, List<OrderItems> orderItems) {
        this.voucherId = voucherId;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItems;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public static Order createOrder(OrderRequestDto requestDto) {
        return new Order(
                requestDto.VoucherId(),
                requestDto.totalPrice(),
                requestDto.orderItems()
        );
    }
}
