package com.eCommerce.eCommerce.dao;

import com.eCommerce.eCommerce.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
