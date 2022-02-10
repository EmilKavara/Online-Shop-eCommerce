package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class JdbcOrderService implements OrderService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Orders order) {
        return jdbcTemplate.update("INSERT INTO orders (idorder, amount, shipping_address, order_date) VALUES(?,?,?,?)",
                new Object[]{order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate()});
    }

    @Override
    public int update(Orders order) {
        return jdbcTemplate.update("UPDATE orders SET idorder=?, amount=?, shipping_address=?, order_date=? WHERE idorder=?",
                new Object[]{order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate(),order.getIdorder()});
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
    public int deleteById(Long id) {
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