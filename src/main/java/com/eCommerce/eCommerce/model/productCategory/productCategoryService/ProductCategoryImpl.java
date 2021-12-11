package com.eCommerce.eCommerce.model.productCategory.productCategoryService;

import com.eCommerce.eCommerce.model.productCategory.ProductCategory;
import com.eCommerce.eCommerce.model.productCategory.productCategoryRepository.ProductCategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryImpl implements ProductCategoryService{
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return (List<ProductCategory>) productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(int idproductCategory) {
        return productCategoryRepository.findById(idproductCategory).orElse(null);
    }

    @Override
    public ProductCategory addOrUpdateProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory deleteProductCategory(int idproductCategory) throws Exception {
        ProductCategory deletedProductCategory=null;
        try{
            deletedProductCategory=productCategoryRepository.findById(idproductCategory).orElse(null);
            if(deletedProductCategory == null){
                throw new Exception("User not available");
            }else{
                productCategoryRepository.deleteById(idproductCategory);
            }
            
        }catch(Exception ex){
            throw ex;
        }
        
        return deletedProductCategory;
    }
    
}
