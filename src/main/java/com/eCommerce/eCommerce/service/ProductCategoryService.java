package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {


    public List<ProductCategory> getAllProductCategory();

    public ProductCategory getProductCategoryById(int idproductCategory);

    public void addOrUpdateProductCategory(ProductCategory category);

    public void deleteProductCategory(int idproductCategory) throws Exception;

    public void update(ProductCategory productCategory, int idproductCategory);
}

