package com.programmers.coffeespring.controller;

import com.programmers.coffeespring.dto.Response;
import com.programmers.coffeespring.dto.VoucherResponseDto;
import com.programmers.coffeespring.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vouchers")
@RequiredArgsConstructor
public class VoucherController {

    private final VoucherService voucherService;

    @GetMapping("/{phoneNumber}")
    public Response<List<VoucherResponseDto>> getCustomerVoucher(@PathVariable String phoneNumber){
        return Response.success(voucherService.getVoucherOwnedByCustomer(phoneNumber));
    }


}
