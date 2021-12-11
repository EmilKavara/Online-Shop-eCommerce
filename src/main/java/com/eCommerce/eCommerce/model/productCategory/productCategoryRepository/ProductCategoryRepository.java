package com.eCommerce.eCommerce.model.productCategory.productCategoryRepository;

import com.eCommerce.eCommerce.model.productCategory.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {
    
}
