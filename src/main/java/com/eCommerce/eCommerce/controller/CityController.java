/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.City;
import com.eCommerce.eCommerce.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public String getAllCities(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "testTable";
    }

}
