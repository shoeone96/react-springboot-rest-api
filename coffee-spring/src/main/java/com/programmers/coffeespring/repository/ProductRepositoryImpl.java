package com.programmers.coffeespring.repository;

import com.programmers.coffeespring.model.Category;
import com.programmers.coffeespring.model.Product;
import com.programmers.coffeespring.util.JdbcUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products",
                JdbcUtils.productRowMapper);
    }

    @Override
    public void save(Product product) {
        int update = jdbcTemplate.update(
                "insert into products(product_name, category, price, description, product_img, created_at, updated_at) " +
                        "values(:productName, :category, :price, :description, :productImg, :createdAt, :updatedAt)",
                JdbcUtils.productInsertMap(product)
        );
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
    }

    @Override
    public void clear() {
        jdbcTemplate.update("delete from products", Collections.emptyMap());
    }

    @Override
    public Optional<Product> findById(long productId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from products where product_id = :productId",
                Collections.singletonMap("productId", productId)
                , JdbcUtils.productRowMapper
        ));
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return jdbcTemplate.query(
                "select * from products where category = :category",
                Collections.singletonMap("category", category.name())
                , JdbcUtils.productRowMapper
        );

    }


}
