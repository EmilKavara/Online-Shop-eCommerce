package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "/productCategory")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/getproductCategory")
    private List<ProductCategory> getAllProductCategory() {
        return productCategoryService.getAllProductCategory();
    }

    @GetMapping("/get/{idproductCategory}")
    private ModelAndView getProductCategoryById(@PathVariable("idproductCategory") int idproductCategory) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(idproductCategory);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProductCategory");
        modelAndView.addObject("productCategory", productCategory);
        return modelAndView;
    }

    @GetMapping("/getproductCategory/{idproductCategory}")
    private ProductCategory getProductCategory(@PathVariable("idproductCategory") int idproductCategory) {
        return productCategoryService.getProductCategoryById(idproductCategory);
    }

    @DeleteMapping("/delete/{idproductCategory}")
    private void deleteProductCategory(@PathVariable("idproductCategory") int idproductCategory) throws Exception {
        productCategoryService.deleteProductCategory(idproductCategory);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ModelAndView addNewProductCategory(@RequestParam String name, @RequestParam String description) {
        ProductCategory pc = new ProductCategory();
        pc.setName(name);
        pc.setDescription(description);
        productCategoryService.addOrUpdateProductCategory(pc);
        return new ModelAndView("productCategory");
    }

    @PostMapping(path = "/productCategory", consumes = "application/x-www-form-urlencoded")
    public @ResponseBody
    ModelAndView update(ProductCategory productCategory) {
        productCategoryService.addOrUpdateProductCategory(productCategory);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("testTable");
        return modelAndView;
    }

    @PostMapping("/productCategory/edit")
    public @ResponseBody
    ModelAndView update(@RequestParam int idproductCategory, @RequestParam String name, @RequestParam String description) {

        ProductCategory productCategory = productCategoryService.getProductCategoryById(idproductCategory);

        if (!name.isEmpty()) {
            productCategory.setName(name);
        }
        if (!description.isEmpty()) {
            productCategory.setDescription(description);
        }
        productCategoryService.addOrUpdateProductCategory(productCategory);
        return new ModelAndView("productCategory");

    }

    @GetMapping("/testTable")
    public String getAllcategories(Model model) {
        List<ProductCategory> categories = productCategoryService.getAllProductCategory();
        model.addAttribute("categories", categories);
        return "testTable";
    }
}