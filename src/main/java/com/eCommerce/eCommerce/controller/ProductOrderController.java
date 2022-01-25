/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.*;
import com.eCommerce.eCommerce.service.ProductOrderService;
import com.eCommerce.eCommerce.service.ProductService;
import com.eCommerce.eCommerce.service.UserService;

import java.math.BigDecimal;
import java.util.*;

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


    /**
     * Returns customer shopping cart. URL request {"/cart"}, method GET.
     *
     * @param userSession requested Authenticated customer.
     * @param model       class object {@link Model}.
     * @return cart page with model attributes.
     */
    @GetMapping("/cart")
    public String getCart(@AuthenticationPrincipal User userSession, Model model) {
        User userFromDB = userService.getUserById(userSession.getIduser());
        model.addAttribute("productList", userFromDB.getOrderList());

        return "cart";
    }


    @PostMapping("/cart/buy")
    public String buy(
            @RequestParam String shippingAddress,
            @RequestParam int amount,
            @RequestParam int idproduct,
            @AuthenticationPrincipal User userSession
    ) {
        Orders order = new Orders();
        long millis = System.currentTimeMillis();
        java.sql.Date date1 = new java.sql.Date(millis);
        order.setOrderDate(date1);
        //User user1 = userService.getUserById(userSession.getIduser());
        User user = userService.getUserById(1);
        order.setUserId(user);
        Product product=productService.getProductById(idproduct);
        order.setAmount(amount);
        order.setShippingAddress(shippingAddress);
        user.getOrderList().add(order);
        userService.saveOrUpdate(user);
        ProductOrder po = new ProductOrder();
        po.setOrderId(new Orders(amount, shippingAddress, date1,user));
        po.setProductId(product);
        po.setQuantity(amount);
        BigDecimal amountB=new BigDecimal(amount);
        BigDecimal total=amountB.multiply(product.getPrice());
        po.setTotal(total);
        productOrderService.saveOrUpdate(po);
        return "redirect:/home";
    }

    @PostMapping("/cart/add")
    public ModelAndView addToCart2(
            @RequestParam("idproduct") int idproduct, @RequestParam("quantity") int quantity,
            @AuthenticationPrincipal User userSession
    ) {
        //HashMap<Product,Integer> productMap=new HashMap<>();
        //HashMap<String, Integer> productMap = new HashMap<>();
        Product product = productService.getProductById(idproduct);
        //productMap.put("string", quantity);
        // List<HashMap<Product,Integer>> list=new ArrayList<>();
        List<Product> list = new ArrayList<>();
        //list.add(productMap);
        list.add(product);
        User user=userService.getUserById(userSession.getIduser());
        ModelAndView modelAndView = new ModelAndView();
        Date date=new Date();
        user.getOrderList().add(new Orders(quantity,"",date,user));
        modelAndView.setViewName("shoppingCart");
        modelAndView.addObject("productList", list);
        modelAndView.addObject("quantity", quantity);
        modelAndView.addObject("productId", idproduct);
        return modelAndView;
    }


    /**
     * Remove product from customer shopping cart and redirects it to "/cart".
     * URL request {"/cart/remove"}, method POST.
     *
     * @param order       the product to be removed from the customer shopping cart.
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

    @PostMapping("/cart/get")
    public ModelAndView getOrders(
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.getUserById(userSession.getIduser());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("shoppingCart");
        modelAndView.addObject("orderList", user.getOrderList());
        return modelAndView;
    }
}
