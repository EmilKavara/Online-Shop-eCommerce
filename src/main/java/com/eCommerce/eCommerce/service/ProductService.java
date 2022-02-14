package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.dao.ProductRepository;
import com.eCommerce.eCommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Cacheable
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public void update(Product product, int idproduct) {
        productRepository.save(product);
    }

    public Optional<Product> findById(Integer idproduct) {
        return productRepository.findById(idproduct);
    }

}
