/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.dao;

import com.eCommerce.eCommerce.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author bnc
 */
public interface ProductRepository extends CrudRepository<Product, Integer>{
    
}
