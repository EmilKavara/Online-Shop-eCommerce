package com.eCommerce.eCommerce.dao;

import com.eCommerce.eCommerce.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {


}
