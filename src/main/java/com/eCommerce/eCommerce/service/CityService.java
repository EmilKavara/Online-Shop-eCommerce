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

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(int idcity) {
        return cityRepository.findById(idcity).get();
    }

    public void deleteCity(int id) {
        cityRepository.deleteById(id);
    }

    public void updateCity(City city, int idcity) {
        cityRepository.save(city);
    }

    public void save(City city) {
        cityRepository.save(city);
    }
}
