package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    
    
    public List<ProductCategory> getAllProductCategory();
    public ProductCategory getProductCategoryById(int idproductCategory);
    public void addOrUpdateProductCategory( ProductCategory category);
    public void deleteProductCategory(int idproductCategory) throws Exception;
    public void update(ProductCategory productCategory,int idproductCategory);
}






/*package com.eCommerce.eCommerce.model.productCategory.productCategoryService;

import com.eCommerce.eCommerce.model.productCategory.ProductCategory;
import java.util.List;


public interface ProductCategoryService {
    
       
    List<ProductCategory> getAllProductCategory();
    ProductCategory getProductCategoryById(int idproductCategory);
    void addOrUpdateProductCategory( ProductCategory category);
    void deleteProductCategory(int idproductCategory) throws Exception;
}*/
