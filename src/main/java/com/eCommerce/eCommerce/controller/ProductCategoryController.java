package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<ProductCategory> productCategories=productCategoryService.getAllProductCategory();
        return productCategories;
    }

     @GetMapping("/get/{idproductCategory}")
    private ModelAndView getProductCategoryById(@PathVariable("idproductCategory") int idproductCategory) {
        ProductCategory productCategory = getProductCategory(idproductCategory);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProductCategory");
        modelAndView.addObject("productCategory", productCategory);
        return modelAndView;
    }
    
    @GetMapping("/getproductCategory/{idproductCategory}")
    private ProductCategory getProductCategory(@PathVariable("idproductCategory") int idproductCategory){
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
    
    @PostMapping(path = "/productCategory", consumes = "application/x-www-form-urlencoded")
    public @ResponseBody ModelAndView update(ProductCategory productCategory) {
        productCategoryService.addOrUpdateProductCategory(productCategory);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testTable");
        return modelAndView;
    }
    
   /* @PutMapping("/update")
    private ProductCategory update(@RequestParam String name,@RequestParam String description){
        ProductCategory pc = new ProductCategory();
        pc.setName(name);
        pc.setDescription(description);
        productCategoryService.addOrUpdateProductCategory(pc);
        return pc;
    }*/
    
     @PostMapping("/productCategory/edit/{idproductCategory}")
    public @ResponseBody ModelAndView update(@PathVariable("idproductCategory") int idproductCategory, @RequestParam String name, @RequestParam String description) {

        ProductCategory productCategory = productCategoryService.getProductCategoryById(idproductCategory);

        if (!name.isEmpty()) {
            productCategory.setName(name);
        }
        if (!description.isEmpty()) {
            productCategory.setDescription(description);
        }
        productCategoryService.addOrUpdateProductCategory(productCategory);
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("productCategory");
        return new ModelAndView("productCategory");

    }
}
 