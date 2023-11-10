package com.programmers.coffeespring.model;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {
    private Long productId;
    private String productName;
    private Category category;
    private long price;
    private byte[] productImg;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Product(String productName, Category category, long price, byte[] productImg, String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.productImg = productImg;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }


    public static Product fromCreateDto(ProductCreateRequestDto requestDto, byte[] img){
        return new Product(
                requestDto.productName(),
                requestDto.category(),
                requestDto.price(),
                img,
                requestDto.description()
                );
    }

    public static Product fromDbMapper(Long productId, String productName, Category category, long price, byte[] productImg, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Product(
                productId,
                productName,
                category,
                price,
                productImg,
                description,
                createdAt,
                updatedAt
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return price == product.price && Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && category == product.category && Objects.equals(productImg, product.productImg) && Objects.equals(description, product.description) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, category, price, productImg, description, createdAt, updatedAt);
    }
}
