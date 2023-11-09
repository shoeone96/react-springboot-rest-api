package com.programmers.coffeespring.controller;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import com.programmers.coffeespring.dto.ProductResponseDto;
import com.programmers.coffeespring.dto.Response;
import com.programmers.coffeespring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public Response<List<ProductResponseDto>> getAllProductList(){
        return Response.success(productService.getAllProducts());
    }

    @PostMapping("/api/v1/products")
    public Response<Void> saveProducts(@RequestBody ProductCreateRequestDto requestDto){
        productService.saveNewProduct(requestDto);
        return Response.success();
    }
}
