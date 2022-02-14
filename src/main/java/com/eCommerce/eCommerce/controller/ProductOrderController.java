package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.*;
import com.eCommerce.eCommerce.service.*;

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
    @Autowired
    private JdbcOrderService orderService;


    @GetMapping("/cart")
    public String getCart(@AuthenticationPrincipal User userSession, Model model) {
        User userFromDB = userService.getUserById(1);
        Orders orders = userFromDB.getOrderList().get(0);
        List<ProductOrder> po = orders.getProductOrderList();
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < po.size(); i++) {
            Product product = po.get(i).getProductId();
            productList.add(product);
        }
        model.addAttribute("productList", productList);
        model.addAttribute("amount", 0);
        model.addAttribute("quantity", 0);
        return "shoppingCart";
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
        User user = userService.getUserById(1);
        order.setUserId(user);
        Product product = productService.getProductById(idproduct);
        order.setAmount(amount);
        order.setShippingAddress(shippingAddress);
        user.getOrderList().add(order);
        ProductOrder po = new ProductOrder();
        po.setOrderId(new Orders(amount, shippingAddress, date1, user));
        po.setProductId(product);
        po.setQuantity(amount);
        BigDecimal amountB = new BigDecimal(amount);
        BigDecimal total = amountB.multiply(product.getPrice());
        po.setTotal(total);
        if (product.getQuantity() >= amount) {
            productOrderService.saveOrUpdate(po);
            userService.saveOrUpdate(user);
            int available = product.getQuantity();
            product.setQuantity(available - amount);
            productService.saveOrUpdate(product);
            return "redirect:/cart/remove";
        } else {
            return "error";
        }

    }

    @PostMapping("/cart/add")
    public ModelAndView addToCart2(
            @RequestParam("idproduct") int idproduct, @RequestParam("quantity") int quantity,
            @AuthenticationPrincipal User userSession
    ) {
        Product product = productService.getProductById(idproduct);
        List<Product> list = new ArrayList<>();
        list.add(product);
        User user = userService.getUserById(1);
        ModelAndView modelAndView = new ModelAndView();
        Date date = new Date();
        user.getOrderList().add(new Orders(quantity, "", date, user));
        modelAndView.setViewName("shoppingCart");
        userService.saveOrUpdate(user);
        modelAndView.addObject("productList", list);
        modelAndView.addObject("quantity", quantity);
        modelAndView.addObject("productId", idproduct);
        return modelAndView;
    }


    @PostMapping("/cart/remove")
    public String removeFromCart(
            @RequestParam(value = "idorder") Orders order,
            @AuthenticationPrincipal User userSession
    ) {
        User user = userService.getUserById(1);

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
