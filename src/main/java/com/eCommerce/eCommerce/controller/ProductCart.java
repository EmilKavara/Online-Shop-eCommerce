package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductCart {

    private Product product;
    private int quantity;

    private List<ProductCart> productCart = new ArrayList<>();

    public ProductCart() {
        this.quantity = 0;
    }

    ;

    public ProductCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public List<ProductCart> getProductCart() {
        return productCart;
    }

    public void setProductCart(List<ProductCart> productCart) {
        this.productCart = productCart;
    }

}
