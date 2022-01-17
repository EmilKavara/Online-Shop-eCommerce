/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import java.util.List;
import com.eCommerce.eCommerce.model.Product;
import com.eCommerce.eCommerce.service.ProductService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

public class ShoppingCart{
    
    @Autowired
    private ProductService productService;
    
    private List<Product> product = new ArrayList<>();
    private List<ProductCart> productsInCart = new ArrayList<>();

    public String addProduct(Product p) {
        ProductCart pc = new ProductCart();
        
        pc.setProduct(p);
        pc.setQuantity(1);
        productsInCart.add(pc);
        return null;
    }
    
    
    
}
