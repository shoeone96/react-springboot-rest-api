package com.programmers.coffeespring.controller;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import com.programmers.coffeespring.dto.ProductResponseDto;
import com.programmers.coffeespring.dto.Response;
import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Response<List<ProductResponseDto>> getProductsByCategory(
            @RequestParam Category category)
    {
        return Response.success(productService.getProductsByCategory(category));
    }

    @PostMapping
    public Response<Void> saveProducts(
            @ModelAttribute ProductCreateRequestDto requestDto)
    {
        productService.saveNewProduct(requestDto);
        return Response.success();
    }
}
