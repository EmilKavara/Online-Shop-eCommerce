package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.Orders;

import java.util.List;

public interface OrderService {

    int save(Orders order);

    int update(Orders order);

    Orders findById(Long id);

    Orders findLast();

    int deleteById(Long id);

    List<Orders> findAll();

    int deleteAll();
}

