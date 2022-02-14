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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    public Model addModelAttribute(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return model;
    }

    @GetMapping("/cities")
    public String GetAllCities(Model model) {
        addModelAttribute(model);
        return "testTable";
    }

    @GetMapping("/registration")
    public String getAllCities(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "registration";
    }

    @GetMapping("/getcity/{idcity}")
    private City getCity(@PathVariable("idcity") int idcity) {
        return cityService.getCityById(idcity);
    }

    @DeleteMapping("/city/{idcity}")
    private void deleteCity(@PathVariable("idcity") int idcity) {
        cityService.deleteCity(idcity);
    }
}
