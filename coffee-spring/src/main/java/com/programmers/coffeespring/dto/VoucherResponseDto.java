package com.programmers.coffeespring.dto;

import com.programmers.coffeespring.model.Voucher;
import com.programmers.coffeespring.model.VoucherType;

public record VoucherResponseDto(
        String voucherName,
        VoucherType voucherType,
        long discountValue
) {

    public static  VoucherResponseDto of(Voucher voucher) {
        return new VoucherResponseDto(
                voucher.getVoucherName(),
                voucher.getVoucherType(),
                voucher.getDiscountValue()
        );
    }
}
