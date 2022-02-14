package com.eCommerce.eCommerce.dao;

import com.eCommerce.eCommerce.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
