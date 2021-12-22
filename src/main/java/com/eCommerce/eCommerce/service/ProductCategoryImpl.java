package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.repository.ProductCategoryRepository;
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


/*package com.eCommerce.eCommerce.model.productCategory.productCategoryService;

import com.eCommerce.eCommerce.model.productCategory.ProductCategory;
import com.eCommerce.eCommerce.model.productCategory.productCategoryRepository.ProductCategoryRepository;
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
        
        List<ProductCategory> cat=new ArrayList<ProductCategory>();
        productCategoryRepository.findAll().forEach(category->cat.add(category));
        return cat;
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
    public void deleteProductCategory(int idproductCategory) throws Exception {
        productCategoryRepository.deleteById(idproductCategory);
    }

    
    

    
    
}*/
