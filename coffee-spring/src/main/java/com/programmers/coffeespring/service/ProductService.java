package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import com.programmers.coffeespring.dto.ProductResponseDto;
import com.programmers.coffeespring.model.Product;
import com.programmers.coffeespring.repository.ProductRepository;
import com.programmers.coffeespring.util.ImgUtil;
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

    public void saveNewProduct(ProductCreateRequestDto requestDto) {
        String img = ImgUtil.saveImage(requestDto.productImg());
        Product product = Product.fromCreateDto(requestDto, img);
        productRepository.save(product);
    }
}
