package com.eCommerce.eCommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ECommerceApplication{// implements CommandLineRunner{

   /*@Autowired
    private JdbcTemplate jdbcTemplate;*/
    
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
/*@GetMapping("/hello")
public String hello(@RequestParam(value = "name", defaultValue = "MyShop") String name) {
return String.format("Hello %s!", name);
}*/

 /*   @Override
    public void run(String... args) throws Exception {
       String sql = "INSERT INTO privilege (name) VALUES (?)";
         
        int result = jdbcTemplate.update(sql, "admin");
         
        if (result > 0) {
            System.out.println("A new row has been inserted.");
        } 
    }*/
}
