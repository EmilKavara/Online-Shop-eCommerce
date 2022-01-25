package com.eCommerce.eCommerce.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {
    List<Map> productList;
    HashMap<Product, Integer> productMap = new HashMap<>();

    Item(List<Map> productList) {
        this.productList = productList;
    }

    public HashMap<Product, Integer> getProductMap() {
        return productMap;
    }

    public void setProductMap(HashMap<Product, Integer> productMap) {
        this.productMap = productMap;
    }

    public String toString() {
        String s = this.productList + ":";
        return s;
    }

    public List<Map> getProductName() {
        return this.productList;
    }
}
