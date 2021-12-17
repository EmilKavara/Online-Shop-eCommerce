/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.dao.CityRepository;
import com.eCommerce.eCommerce.model.City;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities(){
     return cityRepository.findAll();
    }  
}
