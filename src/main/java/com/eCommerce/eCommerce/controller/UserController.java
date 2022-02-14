package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.*;
import com.eCommerce.eCommerce.service.AddressService;
import com.eCommerce.eCommerce.service.UserService;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/getuser")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{iduser}")
    private ModelAndView getUserById(@PathVariable("iduser") int iduser) {
        User user = getUser(iduser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/getuser/{iduser}")
    private User getUser(@PathVariable("iduser") int iduser) {
        return userService.getUserById(iduser);
    }

    @DeleteMapping("/delete/{iduser}")
    private void deleteUser(@PathVariable("iduser") int iduser) {
        userService.delete(iduser);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ModelAndView addNewUser(@RequestParam String firstName, @RequestParam String lastName,
                            @RequestParam String gender, @RequestParam String username, @RequestParam String password, @RequestParam Date dateOfBirth,
                            @RequestParam String street, @RequestParam int number, @RequestParam String cityname,
                            @RequestParam String phone, @RequestParam String email) {
        User n = new User();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        if (gender.equals("1")) {
            n.setGender("male");
        } else {
            n.setGender("female");
        }
        n.setUsername(username);
        n.setPassword(passwordEncoder.encode(password));
        n.setDateOfBirth(dateOfBirth);
        long millis = System.currentTimeMillis();
        Date dateCreated = new Date(millis);
        n.setDateCreated(dateCreated);
        n.setPhone(phone);
        n.setEmail(email);
        Privilege privilege = new Privilege();
        privilege.setName("user");
        privilege.setIdprivilege(2);
        n.setPrivilege(privilege);
        Short num = (short) 2;
        n.setActive(num);
        Address address = new Address();
        address.setNumber(number);
        address.setStreet(street);
        City city = new City();
        city.setIdcity(Integer.valueOf(cityname));
        address.setIdCity(city);
        addressService.saveOrUpdate(address);
        n.setAddressId(address);
        userService.saveOrUpdate(n);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    @PutMapping("/user/update")
    private User update2(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user;
    }

    @PostMapping("/users/edit")
    public @ResponseBody
    ModelAndView update(@PathVariable("iduser") int iduser, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
                        @RequestParam(required = false) String email, @RequestParam(required = false) String phone, @RequestParam(required = false) String username) {
        User user = userService.getUserById(iduser);
        if (!firstName.isEmpty()) {
            user.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            user.setLastName(lastName);
        }
        if (!email.isEmpty()) {
            user.setEmail(email);
        }
        if (!phone.isEmpty()) {
            user.setPhone(phone);
        }
        if (!username.isEmpty()) {
            user.setUsername(username);
        }
        userService.saveOrUpdate(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;

    }

    @PostMapping(path = "/user", consumes = "application/x-www-form-urlencoded")
    public @ResponseBody
    ModelAndView update(User user) {
        userService.update(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        return modelAndView;
    }
}  