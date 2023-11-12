package com.programmers.coffeespring.util;

import com.programmers.coffeespring.dto.OrderItems;
import com.programmers.coffeespring.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JdbcUtils {
    public static RowMapper<Customer> customerMapper = (resultSet, i) ->
            Customer.fromDbMapper(
                    resultSet.getLong("customer_id"),
                    resultSet.getString("customer_name"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("phone_number"),
                    resultSet.getObject("created_at", LocalDateTime.class),
                    resultSet.getObject("updated_at", LocalDateTime.class)
            );

    public static Map<String, Object> productInsertMap(Product product) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("productName", product.getProductName());
        map.put("category", product.getCategory().name());
        map.put("price", product.getPrice());
        map.put("description", product.getDescription());
        map.put("productImg", product.getProductImg());
        map.put("createdAt", product.getCreatedAt());
        map.put("updatedAt", product.getUpdatedAt());
        return map;
    }

    public static RowMapper<Voucher> voucherRowMapper = (resultSet, i) -> {
        Long voucherId = resultSet.getLong("voucher_id");
        String voucherName = resultSet.getString("voucher_name");
        long discountValue = resultSet.getLong("discount_value");
        VoucherType voucherType = VoucherType.valueOf(resultSet.getString("voucher_type"));
        VoucherValid voucherValid = VoucherValid.valueOf(resultSet.getString("voucher_valid"));
        Long customerId = resultSet.getObject("customer_id") != null ?
                resultSet.getLong("customer_id") : null;
        LocalDateTime createdAt = resultSet.getObject("created_at", LocalDateTime.class);
        LocalDateTime updatedAt = resultSet.getObject("updated_at", LocalDateTime.class);
        return Voucher.fromDbMapper(voucherId, voucherName, discountValue, voucherType, voucherValid, customerId, createdAt, updatedAt);
    };


    public static RowMapper<Product> productRowMapper = (resultSet, i) -> Product.fromDbMapper(
            resultSet.getLong("product_id"),
            resultSet.getString("product_name"),
            Category.valueOf(resultSet.getString("category")),
            resultSet.getLong("price"),
            resultSet.getBytes("product_img"),
            resultSet.getString("description"),
            resultSet.getObject("created_at", LocalDateTime.class),
            resultSet.getObject("updated_at", LocalDateTime.class)
    );


    public static Map<String, Object> toOrderParamMap(Order order) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", order.getOrderId());
        map.put("totalPrice", order.getOrderId());
        map.put("createdAt", order.getCreatedAt());
        map.put("updatedAt", order.getUpdatedAt());
        return map;
    }

    public static Map<String, Object> toOrderItemParamMap(long orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItems item) {
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("productId", item.product_id());
        map.put("quantity", item.quantity());
        map.put("createdAt", createdAt);
        map.put("updatedAt", updatedAt);
        return map;
    }
}
