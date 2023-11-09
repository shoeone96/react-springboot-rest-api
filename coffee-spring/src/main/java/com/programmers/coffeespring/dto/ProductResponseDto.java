package com.programmers.coffeespring.dto;

import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;

public record ProductResponseDto(
        Long productId,
        String productName,
        Category category,
        long price,
        String productImg,
        String description) {
    public static ProductResponseDto of(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getProductImg(),
                product.getDescription()
        );
    }
}
