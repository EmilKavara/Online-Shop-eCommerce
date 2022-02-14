package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.dao.ProductOrderRepository;
import com.eCommerce.eCommerce.dao.OrderRepository;
import com.eCommerce.eCommerce.model.ProductOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<ProductOrder> getAllOrders() {
        return productOrderRepository.findAll();
    }

    public ProductOrder getOrderById(int id) {
        return productOrderRepository.findById(id).get();
    }

    public void saveOrUpdate(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
    }

    public void delete(int id) {
        productOrderRepository.deleteById(id);
    }

    public void update(ProductOrder productOrder, int id) {
        productOrderRepository.save(productOrder);
    }

}
