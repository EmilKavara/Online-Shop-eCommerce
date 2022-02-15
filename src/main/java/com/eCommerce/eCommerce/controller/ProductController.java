package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Discount;
import com.eCommerce.eCommerce.model.Product;
import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductCategoryService;
import com.eCommerce.eCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/getproduct")
    private List<Product> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/products")
    private ModelAndView getAllProducts2() {
        List<ProductCategory> categories = productCategoryService.getAllProductCategory();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("products");
        modelAndView.addObject("categories",categories);
        return modelAndView;
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
    public @ResponseBody
    ModelAndView addNewProduct(Product product, @RequestParam(required = false) Integer productCategory) {
        Short num = (short) 1;
        product.setActive(num);

        Discount discount = new Discount();
        discount.setIddiscount(1);

        product.setDiscountId(discount);

        ProductCategory category = new ProductCategory();
        if (productCategory != null) {
            category.setIdproductCategory(productCategory);
        } else {
            category.setIdproductCategory(1);
        }
        product.setCategoryId(category);

        productService.saveOrUpdate(product);

        List<ProductCategory> categories = productCategoryService.getAllProductCategory();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @PostMapping(path = "/product", consumes = "application/x-www-form-urlencoded")
    public @ResponseBody
    ModelAndView update(Product product) {
        productService.saveOrUpdate(product);
        List<ProductCategory> categories = productCategoryService.getAllProductCategory();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("products");
        return modelAndView;
    }

}
