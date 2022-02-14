package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.dao.DiscountRepository;
import com.eCommerce.eCommerce.model.Discount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public Discount getDiscountById(int id) {
        return discountRepository.findById(id).get();
    }

    public void saveOrUpdate(Discount discount) {
        discountRepository.save(discount);
    }

    public void delete(int id) {
        discountRepository.deleteById(id);
    }

    public void update(Discount discount, int id) {
        discountRepository.save(discount);
    }

}
