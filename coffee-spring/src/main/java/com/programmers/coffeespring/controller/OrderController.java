package com.programmers.coffeespring.controller;

import com.programmers.coffeespring.dto.OrderRequestDto;
import com.programmers.coffeespring.dto.Response;
import com.programmers.coffeespring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/orders")
    public Response<Void> requestOrder(
            @RequestBody OrderRequestDto requestDto
    ){
        if(requestDto.getVoucherId() == null){
            orderService.order(requestDto);
            return Response.success();
        }
        orderService.orderWithVoucher(requestDto);
        return Response.success();
    }


}
