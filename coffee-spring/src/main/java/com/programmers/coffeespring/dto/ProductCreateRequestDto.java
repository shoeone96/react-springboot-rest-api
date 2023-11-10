package com.programmers.coffeespring.dto;

import com.programmers.coffeespring.model.Category;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public record ProductCreateRequestDto(
        @NonNull String productName,
        @NonNull Category category,
        @NonNull long price,
        MultipartFile productImg,
        @NonNull String description) {
}
