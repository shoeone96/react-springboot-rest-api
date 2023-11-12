package com.programmers.coffeespring.model;

import com.programmers.coffeespring.dto.VoucherCreateRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Voucher {
    private long voucher_id;
    private final String voucherName;
    private final long discount_value;
    private final VoucherType voucher_type;
    private VoucherValid voucher_valid;
    private long customer_id;
    private final LocalDateTime created_at;
    private LocalDateTime updated_at;

    private Voucher( long discount_value, String voucherName, VoucherType voucher_type) {
        this.voucherName = voucherName;
        this.discount_value = discount_value;
        this.voucher_type = voucher_type;
        this.voucher_valid = VoucherValid.VALID;
        this.created_at = LocalDateTime.now();
        this.updated_at = created_at;
    }

    public static Voucher makeVoucher(VoucherCreateRequestDto requestDto){
        return new Voucher(
                requestDto.discount_value(),
                requestDto.voucherName(),
                requestDto.voucherType()
        );
    }
}
