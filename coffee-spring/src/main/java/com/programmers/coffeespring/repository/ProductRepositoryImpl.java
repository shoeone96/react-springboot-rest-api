package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products",
                productRowMapper);
    }

    @Override
    public void save(Product product) {
        int update = jdbcTemplate.update(
                "insert into products(product_name, category, price, description, product_img, created_at, updated_at) " +
                        "values(:productName, :category, :price, :description, :productImg, :createdAt, :updatedAt)",
                insertMap(product)
        );
        if(update != 1){
            throw new RuntimeException("Nothing was inserted");
        }
    }

    @Override
    public void clear() {
        jdbcTemplate.update("delete from products", Collections.emptyMap());
    }

    private Map<String, Object> insertMap(Product product) {
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

    private final RowMapper<Product> productRowMapper = (resultSet, i) -> Product.fromDbMapper(
            resultSet.getLong("product_id"),
            resultSet.getString("product_name"),
            Category.valueOf(resultSet.getString("category")),
            resultSet.getLong("price"),
            resultSet.getBytes("product_img"),
            resultSet.getString("description"),
            resultSet.getObject("created_at", LocalDateTime.class),
            resultSet.getObject("updated_at", LocalDateTime.class)
    );



}
