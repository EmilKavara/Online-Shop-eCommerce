package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.exception.NotEnoughProductsInStockException;
import com.eCommerce.eCommerce.service.ProductService;
import com.eCommerce.eCommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{idproduct}")
    public ModelAndView addProductToCart(@PathVariable("idproduct") Integer idproduct) {
        productService.findById(idproduct).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{idproduct}")
    public ModelAndView removeProductFromCart(@PathVariable("idproduct") Integer idproduct) {
        productService.findById(idproduct).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}
