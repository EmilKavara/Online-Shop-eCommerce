/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Discount;
import com.eCommerce.eCommerce.model.Product;
import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.model.Product_;
import static com.eCommerce.eCommerce.model.Product_.active;
import com.eCommerce.eCommerce.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "testTable";
    }*/
    @GetMapping("/getproduct")
    private List<Product> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/get/{idproduct}")
    private ModelAndView getProductById(@PathVariable("idproduct") int idproduct) {
        Product product = getProduct(idproduct);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProduct");
        modelAndView.addObject("product",product);
        return modelAndView;
    }
    
    @GetMapping("/getproduct/{idproduct}")
    private Product getProduct(@PathVariable("idproduct") int idproduct) {
        return productService.getProductById(idproduct);
    }

    @RequestMapping(value = "/products/delete/{idproduct}", method = {RequestMethod.DELETE})
    public String delete(@PathVariable int idproduct) {
        productService.delete(idproduct);
        return "redirect:/product/products";
    }

    @DeleteMapping("/deleteProduct/{idproduct}")
    private void deleteProduct(@PathVariable("idproduct") int idproduct) {
        productService.delete(idproduct);
    }

    @PostMapping("/add")
    public String addNewProduct(Product product) {
        Short num = (short) 1;
        product.setActive(num);

        Discount discount = new Discount();
        discount.setIddiscount(1);

        product.setDiscountId(discount);

        ProductCategory category = new ProductCategory();
        category.setIdproductCategory(1);
        product.setCategoryId(category);

        productService.saveOrUpdate(product);
        return "Saved";
    }
    
    /*public @ResponseBody
    String addNewProduct(
            @RequestParam String name, @RequestParam String description,
            @RequestParam BigDecimal price, @RequestParam int quantity) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        Short num = (short) 1;
        product.setActive(num);

        Discount discount = new Discount();
        discount.setIddiscount(1);
        
        product.setDiscountId(discount);

        ProductCategory category = new ProductCategory();
        category.setIdproductCategory(1);
        product.setCategoryId(category);

        productService.saveOrUpdate(product);
        return product;
    }*/

    /*@PutMapping("/update")
    public Product update(Product product) {

        Discount discount = new Discount();
        discount.setIddiscount(1);

        product.setDiscountId(discount);

        ProductCategory category = new ProductCategory();
        category.setIdproductCategory(1);
        product.setCategoryId(category);

        productService.saveOrUpdate(product);
        return product;
    }*/
    
     @PostMapping(value = "/product")
    private Product update(@RequestBody Product product) {
        productService.saveOrUpdate(product);
        return product;
    }

    @PostMapping("/products/edit/{idproduct}")
    public @ResponseBody
    ModelAndView update(@PathVariable("idproduct") int idproduct,@RequestParam String name, @RequestParam String description,
            @RequestParam BigDecimal price, @RequestParam Integer quantity, @RequestParam Integer active) {
        
        Product product=productService.getProductById(idproduct);
        
        if (!name.isEmpty()) {
            product.setName(name);
        }
        if (!description.isEmpty()) {
            product.setDescription(description);
        }
        if (price!=null) {
            product.setPrice(price);
        }
        if (quantity!=null) {
            product.setQuantity(quantity);
        }
        if (active!=null) {
            product.setActive(active);
        } else {
        }
        productService.saveOrUpdate(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        return modelAndView;

    }
    
    /*private Product update(@RequestParam String name, @RequestParam String description,
            @RequestParam BigDecimal price, @RequestParam int quantity, @RequestParam short active) {
        
        Product pr = new Product();
        
        pr.setName(name);
        pr.setDescription(description);
        pr.setPrice(price);
        pr.setQuantity(quantity);

        //Short num = (short) 1;
        pr.setActive(active);

        Discount discount = new Discount();
        discount.setIddiscount(1);
        pr.setDiscountId(discount);

        ProductCategory category = new ProductCategory();
        category.setIdproductCategory(1);
        pr.setCategoryId(category);
        
        
        productService.saveOrUpdate(pr);
        return pr;
    }*/
}
