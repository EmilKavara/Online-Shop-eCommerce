/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author bnc
 */
public interface UserService extends UserDetailsService{
    User save (User user);
}
