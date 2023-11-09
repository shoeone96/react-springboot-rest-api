package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;
import com.programmers.coffeespring.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @AfterEach
    void clear(){
        productRepository.clear();
    }

    @Test
    @DisplayName("정상적으로 저장되는 product service 로직 확인")
    void saveProductServiceSuccess(){
        //given
        ProductCreateRequestDto productCreateRequestDtoEarly = new ProductCreateRequestDto(
                "name",
                Category.COFFEE,
                4000,
                "/default_coffee.png",
                "맛있는 커피입니다."
        );
        ProductCreateRequestDto productCreateRequestDtoAfter = new ProductCreateRequestDto(
                "name",
                Category.COFFEE,
                4000,
                "/default_coffee.png",
                "맛있는 커피입니다."
        );

        //when
        productService.saveNewProduct(productCreateRequestDtoEarly);
        List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts).hasSize(1);

        //then
        productService.saveNewProduct(productCreateRequestDtoAfter);
        List<Product> afterSave = productRepository.findAll();
        assertThat(afterSave).hasSize(2);


    }

    @Test
    @DisplayName("Product를 모두 조회하는 경우 테스트")
    void findAllProductsServiceTest(){
        //given
        ProductCreateRequestDto requestDto = new ProductCreateRequestDto(
                "name",
                Category.COFFEE,
                4000,
                "/default_coffee.png",
                "맛있는 커피입니다."
        );

        //when
        productService.saveNewProduct(requestDto);

        //then
        List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts).hasSize(1);
    }

}