/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class AppController {

    @RequestMapping("/userPage")
    public ModelAndView userPage(){
        return new ModelAndView("userPage");
    }

    @RequestMapping("/adminPage")
    public ModelAndView adminPage(){
        return new ModelAndView ("adminPage");
    }

    @RequestMapping("/loginPage")
    public ModelAndView login(){
        return new ModelAndView("loginPage");
    }

    @RequestMapping("/registration")
    public ModelAndView registration(){
        return new ModelAndView("registration");
    }

    /*@RequestMapping("/product")
    public ModelAndView product(){
        return new ModelAndView("product");
    }*/

    @RequestMapping("/product/add")
    public ModelAndView addProduct(){
        return new ModelAndView("product");
    }

    @RequestMapping("/user/getusers")
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
    public ModelAndView update(){
        return new ModelAndView("product");
    }

    @RequestMapping("/aboutUs")
    public ModelAndView aboutUs(){
        return new ModelAndView("aboutUs");
    }

    @RequestMapping("/productCategory")
    public ModelAndView productCategory(){
        return new ModelAndView("productCategory");
    }
    
    @RequestMapping("/cart")
    public ModelAndView cart(){
        return new ModelAndView("cart");
    }
    
    @RequestMapping("/shopingCart")
    public ModelAndView shopingCart(){
        return new ModelAndView("shopingCart");
    }

}
