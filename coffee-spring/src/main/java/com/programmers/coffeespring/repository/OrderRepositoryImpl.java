package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Order;
import com.programmers.coffeespring.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insert(Order order) {
        jdbcTemplate.update(
                "insert into orders (total_price, created_at, updated_at) values ( :totalPrice, :createdAt, :updatedAt )",
                JdbcUtils.toOrderParamMap(order));
        order.getOrderItemList().forEach(item -> jdbcTemplate.update(
                "insert into order_items(order_id, product_id,  quantity, created_at, updated_at)" +
                        "values(:orderId, :productId, :quantity, :createdAt, :updatedAt)",
                JdbcUtils.toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(), item)));
    }

    @Override
    @Transactional
    public void insertWithVoucher(Order order) {
        jdbcTemplate.update(
                "insert into orders (voucher_id, total_price, created_at, updated_at) values ( :voucherId, :totalPrice, :createdAt, :updatedAt )",
                JdbcUtils.toOrderParamMap(order));
        order.getOrderItemList().forEach(item -> jdbcTemplate.update(
                "insert into order_items(order_id, product_id,  quantity, created_at, updated_at)" +
                        "values(:orderId, :productId, :quantity, :createdAt, :updatedAt)",
                JdbcUtils.toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(), item)));
        jdbcTemplate.update(
                "update vouchers set voucher_valid = 'USED' where voucher_id = :voucherId",
                Collections.singletonMap("voucherId", order.getVoucherId())
        );
    }
}

