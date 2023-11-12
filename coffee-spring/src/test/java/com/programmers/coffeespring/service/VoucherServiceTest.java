package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.VoucherResponseDto;
import com.programmers.coffeespring.model.Voucher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VoucherServiceTest {

    @Autowired
    VoucherService voucherService;

    @Test
    @DisplayName("회원의 핸드폰 번호로 모든 voucher 조회하는 메서드")
    void getVouchersByPhoneNumber(){
        String phoneNumber = "01000000000";
        VoucherService voucherService1 = voucherService;
        List<VoucherResponseDto> vouchers = voucherService1.getVoucherOwnedByCustomer(phoneNumber);

        Assertions.assertThat(vouchers).hasSize(2);
    }

}