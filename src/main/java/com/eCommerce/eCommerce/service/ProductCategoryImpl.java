package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.repository.ProductCategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*@Service
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
    
}*/




import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryImpl implements ProductCategoryService{
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getAllProductCategory() {
       
    List<ProductCategory> productCategory = new ArrayList<ProductCategory>();  
productCategoryRepository.findAll().forEach(cat1 -> productCategory.add(cat1));  
return productCategory;
    }

    @Override
    public ProductCategory getProductCategoryById(int idproductCategory) {
     return productCategoryRepository.findById(idproductCategory).get(); 
    }

    @Override
    public void addOrUpdateProductCategory(ProductCategory category) {
    productCategoryRepository.save(category); 
    }

    @Override
    public void deleteProductCategory(int idproductCategory){
    productCategoryRepository.deleteById(idproductCategory); 
       
    }

    @Override
    public void update(ProductCategory productCategory, int idproductCategory) {
    productCategoryRepository.save(productCategory); 
    }

    

    
    
}
