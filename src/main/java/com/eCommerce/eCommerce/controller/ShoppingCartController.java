package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.exception.NotEnoughProductsInStockException;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.service.ProductService;
import com.eCommerce.eCommerce.service.ShoppingCartService;
import com.eCommerce.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private final ShoppingCartService shoppingCartService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        return modelAndView;
    }

    @GetMapping("/addProduct/{idproduct}")
    public ModelAndView addProductToCart(@PathVariable("idproduct") Integer idproduct) {
        productService.findById(idproduct).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
    }

    @GetMapping("/removeProduct/{idproduct}")
    public ModelAndView removeProductFromCart(@PathVariable("idproduct") Integer idproduct) {
        productService.findById(idproduct).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }

    @GetMapping("/checkout")
    public ModelAndView checkout(@AuthenticationPrincipal User userSession) {
        try {
                User user = userService.getUserById(2);
                shoppingCartService.checkout(user);
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}
