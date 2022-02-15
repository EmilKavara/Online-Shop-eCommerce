package com.eCommerce.eCommerce.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/userPage").setViewName("userPage");
        registry.addViewController("/adminPage").setViewName("adminPage");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/user/getusers").setViewName("user");
        registry.addViewController("/loginPage").setViewName("loginPage");
        registry.addViewController("/orders").setViewName("order");
        registry.addViewController("/shoppingCart").setViewName("shoppingCart");
        registry.addViewController("/userDash").setViewName("userDash");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }
}
