/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Discount;
import com.eCommerce.eCommerce.model.Product;
import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    public Model addModelAttribute(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return model;
    }

    /*@GetMapping("/products")
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
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/getproduct/{idproduct}")
    private Product getProduct(@PathVariable("idproduct") int idproduct) {
        return productService.getProductById(idproduct);
    }

    @GetMapping("/getproducts/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getAllProducts().stream()
                .filter(product -> Objects.equals(product.getCategoryId().getIdproductCategory(), categoryId))
                .collect(Collectors.toList());
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


    @PostMapping(path = "/product", consumes = "application/x-www-form-urlencoded")
    public @ResponseBody
    ModelAndView update(Product product) {
        productService.saveOrUpdate(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testTable");
        return modelAndView;
    }

    @PostMapping("/products/edit/{idproduct}")
    public @ResponseBody
    ModelAndView update(@PathVariable("idproduct") int idproduct, @RequestParam String name, @RequestParam String description,
                        @RequestParam BigDecimal price, @RequestParam Integer quantity, @RequestParam Integer active) {

        Product product = productService.getProductById(idproduct);

        if (!name.isEmpty()) {
            product.setName(name);
        }
        if (!description.isEmpty()) {
            product.setDescription(description);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }
        if (active != null) {
            product.setActive(active);
        }
        productService.saveOrUpdate(product);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product");
        return modelAndView;

    }
}
