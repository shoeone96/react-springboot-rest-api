package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Voucher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VoucherRepositoryImplTest {

    @Autowired
    VoucherRepository voucherRepository;

    @Test
    @DisplayName("고객의 전화번호로 쿠폰을 조회하는 테스트")
    void getVouchersWithPhoneNumber(){
        List<Voucher> byUserId = voucherRepository.findByUserId(1L);

        //DB에 총 두 개의 데이터 존재
        Assertions.assertThat(byUserId).hasSize(2);
        for(Voucher voucher : byUserId){
            Assertions.assertThat(voucher.getCustomerId()).isEqualTo(1L);
        }
    }

}