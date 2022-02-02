/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.ProductCategory;
import com.eCommerce.eCommerce.service.ProductCategoryImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class AppController {
    
    @Autowired
    private ProductCategoryImpl productCategory;
    
    @RequestMapping("/userPage")
    public ModelAndView userPage(){
        List<ProductCategory> categoryList=productCategory.getAllProductCategory();
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("userPage");
        modelAndView.addObject("categories", categoryList);
        return modelAndView;
    }
    
    @RequestMapping("/adminPage")
    public ModelAndView adminPage(){
        return new ModelAndView ("adminPage");
    }
    
    @RequestMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView("loginPage");
    }
    
    @RequestMapping("/productCategory/update")
    public ModelAndView update(){
        return new ModelAndView("productCategory");
    }
    
    @RequestMapping("/productCategory")
    public ModelAndView productCategory(){
        return new ModelAndView("productCategory");
    }
    
     @RequestMapping("/registration")
    public ModelAndView registration(){
        return new ModelAndView("registration");
    }

    @RequestMapping("/product")
    public ModelAndView product(){
        return new ModelAndView("product");
    }

    @RequestMapping("/product/add")
    public ModelAndView addProduct(){
        return new ModelAndView("product");
    }

    @RequestMapping("/user")
    public ModelAndView user(){
        return new ModelAndView("user");
    }

    @RequestMapping("/user/add")
    public ModelAndView add(){
        return new ModelAndView("user");
    }

    @RequestMapping("/order/getallorders")
    public ModelAndView order(){
        return new ModelAndView("order");
    }


    @RequestMapping("/products")
    public ModelAndView products(){
        return new ModelAndView("products");
    }

    @RequestMapping("product/update")
    public ModelAndView updateP(){
        return new ModelAndView("product");
    }
    
     @RequestMapping("/productCategory/add")
    public ModelAndView addProductCategory(){
        return new ModelAndView("productCategory");
    }
    
}