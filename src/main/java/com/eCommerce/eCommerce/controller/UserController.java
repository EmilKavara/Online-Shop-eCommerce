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
  public @ResponseBody String addNewUser (@RequestParam String firstName,@RequestParam String lastName,@RequestParam String username,@RequestParam String password,@RequestParam String email) 
        {
   
    User n = new User();
    n.setFirstName(firstName);
    n.setEmail(email);
    n.setLastName(lastName);
    n.setGender("male");
    n.setUsername(username);
    n.setPassword(password);
    n.setPhone("387123456");
    Privilege privilege=new Privilege();
    privilege.setName("user");
    privilege.setIdprivilege(2);
    n.setPrivilege(privilege);
    Short num=(short)2;
    n.setActive(num);
    Address addressId=new Address();
    addressId.setId(1);
    addressId.setNumber(32);
    addressId.setStreet("Ulica");
    String str="2015-03-01";  
    Date date=Date.valueOf(str);
    n.setDateOfBirth(date);
     long millis=System.currentTimeMillis();   
    Date dateCreated=new Date(millis);
    n.setDateCreated(dateCreated);
    City city=new City();
    city.setIdcity(2);
    city.setName("Sarajevo");
    city.setPostalNumber(72000);
    addressId.setIdCity(city);
    n.setAddressId(addressId);
    userService.saveOrUpdate(n);
    return "Saved";
  }
   
@PutMapping("/user/edit")
private User update(@RequestBody User user)   
{  
userService.saveOrUpdate(user);  
return user;  
}  
}  