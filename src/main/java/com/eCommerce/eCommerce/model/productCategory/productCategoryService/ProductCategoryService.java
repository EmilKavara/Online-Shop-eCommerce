package com.eCommerce.eCommerce.model.productCategory.productCategoryService;

import com.eCommerce.eCommerce.model.productCategory.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
    
    public List<ProductCategory> getAllProductCategory();
    public ProductCategory getProductCategoryById(int idproductCategory);
    public ProductCategory addOrUpdateProductCategory( ProductCategory category);
    public ProductCategory deleteProductCategory(int idproductCategory) throws Exception;
}
