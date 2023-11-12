package com.programmers.coffeespring.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Voucher {
    private Long voucher_id;
    private final String voucherName;
    private final long discountValue;
    private final VoucherType voucherType;
    private VoucherValid voucherValid;
    private Long customer_id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Voucher(String voucherName, long discountValue, VoucherType voucherType) {
        this.voucherName = voucherName;
        this.discountValue = discountValue;
        this.voucherType = voucherType;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }
}
