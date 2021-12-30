package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderService implements OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Order order) {
        return jdbcTemplate.update("INSERT INTO tutorials (idorder, amount, shippingAddress, orderDate) VALUES(?,?,?,?)",
                new Object[] { order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate()});
    }

    @Override
    public int update(Order order) {
        return jdbcTemplate.update("UPDATE order SET idorder=?, amount=?, shippingAddress=?, orderDate=? WHERE idorder=?",
                new Object[] { order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate()});
    }

    @Override
    public Order findById(Long id) {
        try {
            Order order = jdbcTemplate.queryForObject("SELECT * FROM order WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Order.class), id);

            return order;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM order WHERE id=?", id);
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query("SELECT * from order", BeanPropertyRowMapper.newInstance(Order.class));

    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from order");
    }
}
