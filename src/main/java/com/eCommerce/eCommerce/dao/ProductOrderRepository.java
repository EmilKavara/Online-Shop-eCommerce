/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.dao;

import com.eCommerce.eCommerce.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bnc
 */
@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{
    
}
