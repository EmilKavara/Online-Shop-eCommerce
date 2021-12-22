package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    
    
    public List<ProductCategory> getAllProductCategory();
    public ProductCategory getProductCategoryById(int idproductCategory);
    public ProductCategory addOrUpdateProductCategory( ProductCategory category);
    public ProductCategory deleteProductCategory(int idproductCategory) throws Exception;
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
