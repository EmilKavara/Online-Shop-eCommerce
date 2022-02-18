package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcOrderService implements OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Orders order) {
        return jdbcTemplate.update("INSERT INTO orders (amount, shipping_address, order_date,user_id) VALUES(?,?,?,?)",
                new Object[]{order.getAmount(), order.getShippingAddress(), order.getOrderDate(), order.getUserId().getIduser()});
    }

    @Override
    public int update(Orders order) {
        return jdbcTemplate.update("UPDATE orders SET user_id=?, amount=?, shipping_address=?, order_date=? WHERE idorder=?",
                new Object[]{order.getUserId().getIduser(), order.getAmount(), order.getShippingAddress(), order.getOrderDate(), order.getIdorder()});
    }

    @Override
    public Orders findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM orders JOIN users ON users.iduser = orders.user_id WHERE idorder=?",
                    BeanPropertyRowMapper.newInstance(Orders.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public Orders findLast() {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM orders JOIN users ON users.iduser = orders.user_id ORDER BY idorder DESC LIMIT 1",
                    BeanPropertyRowMapper.newInstance(Orders.class));
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM product_order WHERE order_id=?", id);
        return jdbcTemplate.update("DELETE FROM orders WHERE idorder=?", id);
    }

    @Override
    public List<Orders> findAll() {
        return jdbcTemplate.query("SELECT * from orders", BeanPropertyRowMapper.newInstance(Orders.class));

    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from orders");
    }
}