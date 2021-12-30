/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.dao.ProductRepository;
import com.eCommerce.eCommerce.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts(){
     return productRepository.findAll();
    }
    
   /* public List<Product> getAllProducts()   
{  
List<Product> product = new ArrayList<>();  
productRepository.findAll().forEach(product1 -> product.add(product1));  
return product;  
}  */
  
public Product getProductById(int id)   
{  
return productRepository.findById(id).get();  
}  
  
public void saveOrUpdate(Product product)   
{  
productRepository.save(product);  
}  

public void delete(int id)   
{  
productRepository.deleteById(id);  
}  
 
public void update(Product product, int idproduct)   
{  
productRepository.save(product);  
}  
    
}
