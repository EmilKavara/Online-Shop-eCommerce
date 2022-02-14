package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.model.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.eCommerce.eCommerce.dao.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        List<User> user = new ArrayList<User>();
        userRepository.findAll().forEach(user1 -> user.add(user1));
        return user;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public int update(User user) {

        return jdbcTemplate.update("UPDATE users SET iduser=?, first_name=?, last_name=?, username=?, email=?,phone=? WHERE iduser=?",
                new Object[]{user.getIduser(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPhone(), user.getIduser()});
    }
}  