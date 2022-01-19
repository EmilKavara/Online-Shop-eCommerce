/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Discount;
import com.eCommerce.eCommerce.service.DiscountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiscountController {

    @Autowired
    private DiscountService discountService;
    
    public Model addModelAttribute(Model model){
        model.addAttribute("discounts", discountService.getAllDiscounts());
        return model;
    }

    @GetMapping("/editProduct")
    public String getAllDiscounts(Model model) {
        List<Discount> discounts = discountService.getAllDiscounts();
        model.addAttribute("discounts", discounts);
        return "editProduct";
    }
}
