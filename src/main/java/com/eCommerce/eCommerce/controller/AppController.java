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
    
    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    
    @RequestMapping("/registration")
    public ModelAndView registration(){
        return new ModelAndView("registration");
    }
    
    @RequestMapping("/user/getusers")
    public ModelAndView user(){
        return new ModelAndView("user");
    }
    
    @RequestMapping("/user/add")
    public ModelAndView add(){
        return new ModelAndView("user");
    }

    @RequestMapping("/order")
    public ModelAndView order(){
        return new ModelAndView("order");
    }

}
