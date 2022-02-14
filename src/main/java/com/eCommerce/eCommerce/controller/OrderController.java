package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Order;
import com.eCommerce.eCommerce.model.Orders;
import com.eCommerce.eCommerce.service.JdbcOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    JdbcOrderService jdbcOrderService;

    @GetMapping("/getallorders")
    public ResponseEntity<List<Orders>> getAllOrders(@RequestParam(required = false) String order) {
        try {
            List<Orders> orders = new ArrayList<Orders>();

            if (order == null)
                orders.addAll(jdbcOrderService.findAll());

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getorder/{idorder}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("idorder") long idorder) {
        Orders order = jdbcOrderService.findById(idorder);

        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{idorder}")
    private ModelAndView getProductById(@PathVariable("idorder") int idorder) {
        Orders order = jdbcOrderService.findById((long) idorder);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editOrder");
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @PostMapping("/addorder")
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        try {
            jdbcOrderService.save(new Orders(order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate()));
            return new ResponseEntity<>("New order created.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateorder/{idorder}")
    public ResponseEntity<String> updateOrder(@PathVariable("idorder") long idorder, @RequestBody Order order) {
        Orders _order = jdbcOrderService.findById(idorder);

        if (_order != null) {
            _order.setIdorder((int) idorder);
            _order.setAmount(order.getAmount());
            _order.setShippingAddress(order.getShippingAddress());
            _order.setOrderDate(order.getOrderDate());

            jdbcOrderService.update(_order);
            return new ResponseEntity<>("Order updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find order with id=" + idorder, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/update", consumes = "application/x-www-form-urlencoded")
    public @ResponseBody
    ModelAndView update(Orders order) {
        jdbcOrderService.update(order);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @DeleteMapping("/deleteorder/{idorder}")
    public ResponseEntity<String> deleteOrder(@PathVariable("idorder") long idorder) {
        try {
            int result = jdbcOrderService.deleteById(idorder);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find order with id=" + idorder, HttpStatus.OK);
            }
            return new ResponseEntity<>("Order deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteallorders")
    public ResponseEntity<String> deleteAllOrders() {
        try {
            int numRows = jdbcOrderService.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " order(s)", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete orders.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}