package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import com.programmers.coffeespring.dto.ProductResponseDto;
import com.programmers.coffeespring.model.Product;
import com.programmers.coffeespring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDto::of)
                .toList();
    }
    
}
