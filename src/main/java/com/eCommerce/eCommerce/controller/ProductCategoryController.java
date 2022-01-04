package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path="/productCategory")
public class ProductCategoryController {
    
    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/getproductCategory")
    private List<ProductCategory> getAllProductCategory(){
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/getproductCategory/{idproductCategory}")
    private ProductCategory getProductCategoryById(@PathVariable("idproductCategory") int idproductCategory){
        return productCategoryService.getProductCategoryById(idproductCategory);
    }
    
    @DeleteMapping("/delete/{idproductCategory}")
    private void deleteProductCategory(@PathVariable("idproductCategory") int idproductCategory) throws Exception{
        productCategoryService.deleteProductCategory(idproductCategory);
    }
    
    @PostMapping(path="/add")
    public @ResponseBody ModelAndView addNewProductCategory(@RequestParam String name,@RequestParam String description){
        ProductCategory pc = new ProductCategory();
        pc.setName(name);
        pc.setDescription(description);
        productCategoryService.addOrUpdateProductCategory(pc);
        return new ModelAndView("productCategory");
    }
    
    @PutMapping("/productCategory")
    private ProductCategory update(@RequestBody ProductCategory productCategory){
       
        productCategoryService.addOrUpdateProductCategory(productCategory);
        return productCategory;
    }
    
    @PostMapping("/productCategories/edit/{idproductCategory}")
     public @ResponseBody
    ModelAndView update(@PathVariable("idproductCategory") int idproductCategory, @RequestParam(required = false) String name, @RequestParam(required = false) String description) {
        ProductCategory pc = productCategoryService.getProductCategoryById(idproductCategory);
        if (!name.isEmpty()) {
            pc.setName(name);
        }
        if (!description.isEmpty()) {
            pc.setDescription(description);
        }
        productCategoryService.addOrUpdateProductCategory(pc);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pc");
        return modelAndView;

    }
 }  