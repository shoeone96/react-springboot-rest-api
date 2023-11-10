package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.ProductCreateRequestDto;
import com.programmers.coffeespring.dto.ProductResponseDto;
import com.programmers.coffeespring.exception.CafeException;
import com.programmers.coffeespring.exception.ErrorCode;
import com.programmers.coffeespring.model.Product;
import com.programmers.coffeespring.repository.ProductRepository;
import com.programmers.coffeespring.util.ImgUtil;
import javafx.scene.image.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
        try {
            byte[] imgBytes = requestDto.productImg().getBytes();
            Product product = Product.fromCreateDto(requestDto, imgBytes);
            productRepository.save(product);
        } catch (IOException e) {
            throw new CafeException(ErrorCode.COMPRESS_IMG_ERROR);
        }
    }


}
