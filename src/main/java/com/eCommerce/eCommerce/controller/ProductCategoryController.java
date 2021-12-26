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
    private ProductCategory getProductCategory(@PathVariable("idproductCategory") int idproductCategory){
        return productCategoryService.getProductCategoryById(idproductCategory);
    }
    
    @DeleteMapping("/delete/{idproductCategory}")
    private void deleteProductCategory(@PathVariable("idproductCategory") int idproductCategory) throws Exception{
        productCategoryService.deleteProductCategory(idproductCategory);
    }
    
    @PostMapping(path="/add")
    public @ResponseBody String addNewProductCategory(@RequestParam String name,@RequestParam String description){
        ProductCategory pc = new ProductCategory();
        pc.setName(name);
        pc.setDescription(description);
        productCategoryService.addOrUpdateProductCategory(pc);
        return "Saved";
    }
    
    @PutMapping("/update")
    private ProductCategory update(@RequestParam String name,@RequestParam String description){
        ProductCategory pc = new ProductCategory();
        pc.setName(name);
        pc.setDescription(description);
        productCategoryService.addOrUpdateProductCategory(pc);
        return pc;
    }
}
    
   /* @GetMapping("/allProductCategory")
    public ResponseEntity<List<ProductCategory>> getAllProductCategory(){
        List<ProductCategory> productCategory=null;
        try{
            productCategory= productCategoryService.getAllProductCategory();
            
        }catch(Exception ex){
            ex.getMessage();
        }
        
        return new ResponseEntity<List<ProductCategory>> (productCategory, HttpStatus.OK);
    }
    
     @GetMapping("/getById/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable("id")int idproductCategory){
        ProductCategory productCategory=null;
        try{
            productCategory= productCategoryService.getProductCategoryById(idproductCategory);
            
        }catch(Exception ex){
            ex.getMessage();
        }
        
        return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
    }
    
    @PostMapping("/addOrUpdate")
    public ResponseEntity<ProductCategory> addOrUpdate(@RequestBody ProductCategory productCat){
        ProductCategory productCategory=null;
        try{
            productCategory= productCategoryService.addOrUpdateProductCategory(productCat);
            
        }catch(Exception ex){
            ex.getMessage();
        }
        
        return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductCategory> addOrUpdate(@PathVariable("id")int idproductCategory){
        ProductCategory productCategory=null;
        try{
            productCategory= productCategoryService.deleteProductCategory(idproductCategory);
            
        }catch(Exception ex){
            ex.getMessage();
        }
        
        return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
    }
    
}*/










/*package com.eCommerce.eCommerce.model.productCategory.productCategoryController;

import com.eCommerce.eCommerce.model.productCategory.ProductCategory;
import com.eCommerce.eCommerce.model.productCategory.productCategoryService.ProductCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;*/

   /* @GetMapping("/getAllProductCategory")
    private List<ProductCategory> getAllProductCategory() {
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/getAllProductCategory/{idproductCategory}")
    private ProductCategory getProductCategory(@PathVariable("id") int idproductCategory) {
        return productCategoryService.getProductCategoryById(idproductCategory);
    }

    @PostMapping(path="/add")
    
  }

    @DeleteMapping("/productCategory/{idproductCategory}")
    private void deleteProductCategory(@PathVariable("idproductCategory") int idproductCategory){
        productCategoryService.delete(idproductCategory);
    }

}*/
