package com.programmers.coffeespring.dto;

import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;
import org.springframework.lang.NonNull;

import java.util.Base64;

public record ProductResponseDto(
        @NonNull Long productId,
        @NonNull String productName,
        @NonNull Category category,
        @NonNull long price,
        @NonNull String productImg,
        @NonNull String description) {
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
