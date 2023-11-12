package com.programmers.coffeespring.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Voucher {
    private Long voucherId;
    private final String voucherName;
    private final long discountValue;
    private final VoucherType voucherType;
    private VoucherValid voucherValid;
    private Long customerId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Voucher(String voucherName, long discountValue, VoucherType voucherType) {
        this.voucherName = voucherName;
        this.discountValue = discountValue;
        this.voucherType = voucherType;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public static Voucher fromDbMapper(
            Long voucherId,
            String voucherName,
            long discountValue,
            VoucherType voucherType,
            VoucherValid voucherValid,
            Long customerId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Voucher(
                voucherId,
                voucherName,
                discountValue,
                voucherType,
                voucherValid,
                customerId,
                createdAt,
                updatedAt
        );
    }
}
