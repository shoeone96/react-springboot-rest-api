package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository {

    List<Voucher> findByUserId(Long userId);

    Optional<Voucher> findById(Long voucherId);
}
