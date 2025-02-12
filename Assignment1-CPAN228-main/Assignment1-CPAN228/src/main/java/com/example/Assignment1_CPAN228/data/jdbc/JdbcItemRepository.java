package com.example.Assignment1_CPAN228.data.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Assignment1_CPAN228.data.ItemRepository;
import com.example.Assignment1_CPAN228.model.Fashion_Brands;
import com.example.Assignment1_CPAN228.model.Item;

@Repository
public class JdbcItemRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Item> findAll() {
        String sql = "SELECT * FROM item";
        return jdbcTemplate.query(sql, new ItemRowMapper());
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[] { id }, new ItemRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Item save(Item item) {
        if (item.getCreatedAt() == null) {
            item.setCreatedAt(LocalDateTime.now());
        }

        String sql = "INSERT INTO item (createdAt, name, description, price, releaseDate, brand) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, item.getCreatedAt(), item.getName(),
                item.getDescription(), item.getPrice(),
                item.getReleaseDate(), item.getBrand().name());

        return item;
    }

    private static class ItemRowMapper implements RowMapper<Item> {
        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Item(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getTimestamp("releaseDate").toLocalDateTime(),
                    Fashion_Brands.valueOf(rs.getString("brand")));
        }
    }

}
