package com.eCommerce.eCommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan({"com.eCommerce.eCommerce"})
@EntityScan("com.eCommerce.eCommerce")
@EnableJpaRepositories("com.eCommerce.eCommerce")
public class ECommerceApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }
}
