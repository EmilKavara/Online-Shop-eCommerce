package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    String addNewProductCategory(@RequestParam String name, @RequestParam String description) {
        ProductCategory pc = new ProductCategory();
        pc.setName(name);
        pc.setDescription(description);
        productCategoryService.addOrUpdateProductCategory(pc);
        return "Saved";
    }

    @PutMapping("/update")
    private ProductCategory update(@RequestParam String name, @RequestParam String description) {
        ProductCategory pc = new ProductCategory();
        pc.setName(name);
        pc.setDescription(description);
        productCategoryService.addOrUpdateProductCategory(pc);
        return pc;
    }

    public Model addModelAttribute(Model model) {
        ProductCategory category = new ProductCategory();
        model.addAttribute("categories", productCategoryService.getAllProductCategory());
        return model;
    }

    @GetMapping("/testTable")
    public String getAllcategories(Model model) {
        List<ProductCategory> categories = productCategoryService.getAllProductCategory();
        model.addAttribute("categories", categories);
        return "testTable";
    }
}