package com.eCommerce.eCommerce.dao;

import com.eCommerce.eCommerce.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
}  