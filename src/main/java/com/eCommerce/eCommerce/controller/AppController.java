/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductCategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class AppController {

    @Autowired
    private ProductCategoryImpl productCategory;

    @RequestMapping("/userPage")
    public ModelAndView userPage() {
        List<ProductCategory> categoryList = productCategory.getAllProductCategory();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        modelAndView.addObject("categories", categoryList);
        return modelAndView;
    }

    @RequestMapping("/adminPage")
    public ModelAndView adminPage() {
        return new ModelAndView("adminPage");
    }

    @RequestMapping(value = "/loginPage")
    public ModelAndView loginPage() {
        return new ModelAndView("loginPage");
    }

    @RequestMapping("/registration")
    public ModelAndView registration() {
        return new ModelAndView("registration");
    }

    @RequestMapping("/product")
    public ModelAndView product() {
        return new ModelAndView("product");
    }

    @RequestMapping("/product/add")
    public ModelAndView addProduct() {
        return new ModelAndView("product");
    }

    @RequestMapping("/user/getusers")
    public ModelAndView user() {
        return new ModelAndView("user");
    }

    @RequestMapping("/user/add")
    public ModelAndView add() {
        return new ModelAndView("user");
    }

    @RequestMapping("/order/getallorders")
    public ModelAndView order() {
        return new ModelAndView("order");
    }


    @RequestMapping("/testTable")
    public ModelAndView testTable() {
        return new ModelAndView("testTable");
    }

    @RequestMapping("product/update")
    public ModelAndView update() {
        return new ModelAndView("product");
    }

    @RequestMapping("/aboutUs")
    public ModelAndView aboutUs() {
        return new ModelAndView("aboutUs");
    }

    @RequestMapping("/productCategory/getproductCategory")
    public ModelAndView productCategory() {
        return new ModelAndView("productCategory");
    }

    @RequestMapping("/product/getproduct")
    public ModelAndView productsByCategory() {
        return new ModelAndView("productsByCategory");
    }

    @RequestMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        return new ModelAndView("shopingCart");
    }

}
