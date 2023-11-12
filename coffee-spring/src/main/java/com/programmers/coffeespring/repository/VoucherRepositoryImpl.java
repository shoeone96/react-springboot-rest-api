package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Voucher;
import com.programmers.coffeespring.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VoucherRepositoryImpl implements VoucherRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<Voucher> findByUserId(Long customerId) {
        return jdbcTemplate.query(
                "select * from vouchers where customer_id = :customerId",
                Collections.singletonMap("customerId", customerId),
                JdbcUtils.voucherRowMapper
        );
    }

    @Override
    public Optional<Voucher> findById(Long voucherId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from vouchers where voucher_id = :voucherId",
                Collections.singletonMap("voucherId", voucherId),
                JdbcUtils.voucherRowMapper
        ));
    }
}
