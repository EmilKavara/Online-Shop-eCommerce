/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.service.ProductOrderService;
import com.eCommerce.eCommerce.service.ProductService;
import com.eCommerce.eCommerce.service.UserService;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.model.Orders;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", productOrderService.getAllOrders());

        return modelAndView;
    }
    
    /**
     * Returns customer shopping cart.
     * URL request {"/cart"}, method GET.
     *
     * @param userSession requested Authenticated customer.
     * @param model       class object {@link Model}.
     * @return cart page with model attributes.
     */
    @GetMapping("/cart")
    public String getCart(@AuthenticationPrincipal User userSession, Model model) {
        User userFromDB = userService.getUserById(userSession.getIduser());
        model.addAttribute("products", userFromDB.getOrderList());

        return "cart";
    }
    
    /**
     * Adds a product to the customer shopping cart and redirects it to "/cart".URL request {"/cart/add"}, method POST.
     *
     * @param order
     * @param product     the product to add to the cart.
     * @param userSession request Authenticated customer.
     * @return redirect to cart page.
     */
    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("add") Orders order,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.getUserById(userSession.getIduser());
        user.getOrderList().add(order);
        userService.saveOrUpdate(user);

        return "redirect:/cart";
    }
    
    /**
     * Remove product from customer shopping cart and redirects it to "/cart".
     * URL request {"/cart/remove"}, method POST.
     *
     * @param order     the product to be removed from the customer shopping cart.
     * @param userSession request Authenticated customer.
     * @return redirect to cart page.
     */
    @PostMapping("/cart/remove")
    public String removeFromCart(
            @RequestParam(value = "idorder") Orders order,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.getUserById(userSession.getIduser());
                
        if (order != null) {
            user.getOrderList().remove(order);
                    
        }

        userService.saveOrUpdate(user);

        return "redirect:/cart";
    }
    

    /*@GetMapping("/cart/addProduct/{idproduct}")
    public ModelAndView addProductToCart(@PathVariable("idproduct") int idproduct) {
        productService.getProductById(idproduct).ifPresent(productOrderService.saveOrUpdate(productOrder));
        return shoppingCart();
    }

    @GetMapping("/cart/removeProduct/{idproduct}")
    public ModelAndView removeProductFromCart(@PathVariable("idproduct") int idproduct) {
        productService.getProductById(idproduct).ifPresent(productOrderService.delete(productService));
        return shoppingCart();
}*/
}
