package com.eCommerce.eCommerce;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGenerator {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);
        
        System.out.println("Your encoded password is " + encodedPassword);
    }
    
}
