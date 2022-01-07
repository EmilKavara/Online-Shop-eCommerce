package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.Order;
import com.eCommerce.eCommerce.model.User;
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
        return jdbcTemplate.update("INSERT INTO tutorials (idorder, amount, shipping_address, order_date) VALUES(?,?,?,?)",
                new Object[]{order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate()});
    }

    @Override
    public int update(Order order) {
        return jdbcTemplate.update("UPDATE orders SET idorder=?, amount=?, shipping_address=?, order_date=? WHERE idorder=?",
                new Object[]{order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate(),order.getIdorder()});
    }

    @Override
    public Order findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM orders JOIN users ON users.iduser = orders.user_id WHERE idorder=?",
                    BeanPropertyRowMapper.newInstance(Order.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM orders WHERE idorder=?", id);
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query("SELECT * from orders", BeanPropertyRowMapper.newInstance(Order.class));

    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from orders");
    }
}
