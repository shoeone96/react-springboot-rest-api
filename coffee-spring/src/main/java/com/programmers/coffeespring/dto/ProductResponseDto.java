package com.programmers.coffeespring.dto;

import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;

import java.util.Base64;

public record ProductResponseDto(
        Long productId,
        String productName,
        Category category,
        long price,
        String productImg,
        String description) {
    public static ProductResponseDto of(Product product) {
        String productImg = Base64.getEncoder().encodeToString(product.getProductImg());
        return new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                productImg,
                product.getDescription()
        );
    }
}
