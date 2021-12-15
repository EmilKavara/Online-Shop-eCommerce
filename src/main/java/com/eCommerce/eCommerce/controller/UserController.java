/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Address;
import com.eCommerce.eCommerce.model.City;
import com.eCommerce.eCommerce.model.Privilege;
import com.eCommerce.eCommerce.model.User;
import com.eCommerce.eCommerce.service.UserService;
import java.sql.Date;
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.DeleteMapping;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.PostMapping;  
import org.springframework.web.bind.annotation.PutMapping;  
import org.springframework.web.bind.annotation.RequestBody;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;  
 
  
@RestController
@RequestMapping(path="/user")
public class UserController   
{  
    
  
@Autowired  
UserService userService;  
  
@GetMapping("/getuser")  
private List<User> getAllUsers()   
{  
return userService.getAllUsers();  
}  
  
@GetMapping("/getuser/{iduser}")  
private User getUser(@PathVariable("iduser") int iduser)   
{  
return userService.getUserById(iduser);  
}  
  
@DeleteMapping("/user/{iduser}")  
private void deleteUser(@PathVariable("iduser") int iduser)   
{  
userService.delete(iduser);  
}  

@PostMapping(path="/add") 
  public @ResponseBody String addNewUser (@RequestParam String firstName,@RequestParam String lastName,
          @RequestParam String username,@RequestParam String password,@RequestParam Date dateOfBirth,
          @RequestParam String street, @RequestParam int number, @RequestParam String name, @RequestParam int postalNumber,
          @RequestParam String phone, @RequestParam String email) 
        {
   
    User n = new User();
    n.setFirstName(firstName);
    n.setLastName(lastName);
    n.setGender("male");
    n.setUsername(username);
    n.setPassword(password);
    //String str="2015-03-01"; 
    //Date date=Date.valueOf(str);
    n.setDateOfBirth(dateOfBirth);
     long millis=System.currentTimeMillis();   
    Date dateCreated=new Date(millis);
    n.setDateCreated(dateCreated);
    n.setPhone(phone);
    n.setEmail(email);
    
    Privilege privilege=new Privilege();
    privilege.setName("user");
    privilege.setIdprivilege(2);
    n.setPrivilege(privilege);
    
    Short num=(short)2;
    n.setActive(num);
    
    Address addressId=new Address();
    //addressId.setId(1);
    addressId.setNumber(number);
    addressId.setStreet(street);
    
    
    City city=new City();
    //city.setIdcity(2);
    city.setName(name);
    city.setPostalNumber(postalNumber);
    
    addressId.setIdCity(city);
    n.setAddressId(addressId);
    
    userService.saveOrUpdate(n);
    return "Saved"; 
  }
   
@PutMapping("/user")  
private User update(@RequestBody User user)   
{  
userService.saveOrUpdate(user);  
return user;  
}  
}
