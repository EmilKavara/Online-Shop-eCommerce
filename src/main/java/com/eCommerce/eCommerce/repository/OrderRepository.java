package com.eCommerce.eCommerce.repository;

import com.eCommerce.eCommerce.model.Order;

import java.util.List;

public interface OrderRepository {
    int save(Order order);

    int update(Order order);

    Order findById(Long id);

    int deleteById(Long id);

    List<Order> findAll();

    int deleteAll();
}
