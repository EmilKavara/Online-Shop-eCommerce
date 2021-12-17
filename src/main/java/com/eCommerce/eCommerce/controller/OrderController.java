package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Order;
import com.eCommerce.eCommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String order) {
        try {
            List<Order> orders = new ArrayList<Order>();

            if (order == null)
                orderService.findAll().forEach(orders::add);

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order/{idorder}")
    public ResponseEntity<Order> getOrderById(@PathVariable("idorder") long idorder) {
        Order order = orderService.findById(idorder);

        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        try {
            orderService.save(new Order(order.getIdorder(), order.getAmount(), order.getShippingAddress(), order.getOrderDate()));
            return new ResponseEntity<>("New order created.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/order/{idorder}")
    public ResponseEntity<String> updateOrder(@PathVariable("idorder") long idorder, @RequestBody Order order) {
        Order _order = orderService.findById(idorder);

        if (_order != null) {
            _order.setIdorder((int) idorder);
            _order.setAmount(order.getAmount());
            _order.setShippingAddress(order.getShippingAddress());
            _order.setOrderDate(order.getOrderDate());

            orderService.update(_order);
            return new ResponseEntity<>("Order updated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find order with id=" + idorder, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/order/{idorder}")
    public ResponseEntity<String> deleteOrder(@PathVariable("idorder") long idorder) {
        try {
            int result = orderService.deleteById(idorder);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find order with id=" + idorder, HttpStatus.OK);
            }
            return new ResponseEntity<>("Order deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/order")
    public ResponseEntity<String> deleteAllOrders() {
        try {
            int numRows = orderService.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " order(s)", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete orders.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
