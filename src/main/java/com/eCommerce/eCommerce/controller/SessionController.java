package com.eCommerce.eCommerce.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/")
    public @ResponseBody
    ResponseEntity<List> getMessage(Model model, HttpSession session) {
        List greetings = (List) session.getAttribute("GREETING_MESSAGES");
        if (greetings == null) {
            greetings = new ArrayList<>();
        }

        return new ResponseEntity<List>(greetings, HttpStatus.OK);
    }

    @PostMapping("/messages")
    public @ResponseBody
    ResponseEntity<List> saveMessage(@RequestParam("message") String greeting, HttpServletRequest request) {
        List greetings = (List) request.getSession().getAttribute("GREETING_MESSAGES");
        if (greetings == null) {
            greetings = new ArrayList<>();
            request.getSession().setAttribute("GREETING_MESSAGES", greetings);
        }
        greetings.add(greeting);
        return new ResponseEntity<List>(greetings, HttpStatus.OK);
    }
}