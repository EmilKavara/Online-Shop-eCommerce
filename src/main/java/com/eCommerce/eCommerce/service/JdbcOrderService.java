package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.Order;
import com.eCommerce.eCommerce.model.Orders;
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
    public int save(Orders order) {
        return jdbcTemplate.update("INSERT INTO orders (idorder, amount, shipping_address, order_date,user_id) VALUES(?,?,?,?,?)",
                new Object[] { order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate(),order.getUserId().getIduser()});
    }

    @Override
    public int update(Orders order) {
        return jdbcTemplate.update("UPDATE orders SET idorder=?, amount=?, shipping_address=?, order_date=? WHERE idorder=?",
                new Object[] { order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate()});
    }

    @Override
    public Orders findById(Long id) {
        try {
            Orders order = jdbcTemplate.queryForObject("SELECT * FROM orders WHERE idorder=?",
                    BeanPropertyRowMapper.newInstance(Orders.class), id);

            return order;
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
