package com.programmers.coffeespring.service;

import com.programmers.coffeespring.dto.VoucherResponseDto;
import com.programmers.coffeespring.exception.CafeException;
import com.programmers.coffeespring.exception.ErrorCode;
import com.programmers.coffeespring.model.Customer;
import com.programmers.coffeespring.model.Voucher;
import com.programmers.coffeespring.repository.CustomerRepository;
import com.programmers.coffeespring.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final CustomerRepository customerRepository;
    public List<VoucherResponseDto> getVoucherOwnedByCustomer(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CafeException(ErrorCode.CUSTOMER_NOT_FOUND));

        return voucherRepository.findByUserId(customer.getCustomerId())
                .stream()
                .map(VoucherResponseDto::of)
                .toList();
    }

}
