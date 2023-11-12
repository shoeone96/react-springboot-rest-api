package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import com.programmers.coffeespring.dto.ProductResponseDto;
import com.programmers.coffeespring.exception.CafeException;
import com.programmers.coffeespring.exception.ErrorCode;
import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;
import com.programmers.coffeespring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<ProductResponseDto> getProductsByCategory(Category category) {
        return productRepository.findAllByCategory(category)
                .stream()
                .map(ProductResponseDto::of)
                .toList();
    }

    public void saveNewProduct(ProductCreateRequestDto requestDto) {
        try {
            byte[] imgBytes = requestDto.productImg().getBytes();
            Product product = Product.fromCreateDto(requestDto, imgBytes);
            productRepository.save(product);
        } catch (IOException e) {
            throw new CafeException(ErrorCode.COMPRESS_IMG_ERROR);
        }
    }


}
