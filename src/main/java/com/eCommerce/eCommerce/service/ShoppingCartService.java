package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.exception.NotEnoughProductsInStockException;
import com.eCommerce.eCommerce.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
