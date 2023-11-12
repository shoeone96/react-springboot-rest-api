package com.programmers.coffeespring.dto;


import java.time.LocalDateTime;

public record OrderItems(
        Long product_id,
        Long quantity,
        long price,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
}
