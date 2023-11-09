package com.programmers.coffeespring.dto;

import com.programmers.coffeespring.model.Category;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public record ProductCreateRequestDto(
        @NonNull String productName,
        @NonNull Category category,
        @NonNull long price,
        @NonNull String productImg,
        @NonNull String description) {
}
