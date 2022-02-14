package com.eCommerce.eCommerce.controller;

import com.eCommerce.eCommerce.model.Address;
import com.eCommerce.eCommerce.service.AddressService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@RequestMapping(path = "/address")
@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public String getAllAddresses(Model model) {
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses", addresses);
        return "testTable";
    }

    @GetMapping("/getaddress")
    private List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/getaddresses/{idaddress}")
    private Address getAddress(@PathVariable("idaddress") int id) {
        return addressService.getAddressById(id);
    }

    @DeleteMapping("/address/{idaddress}")
    private void deleteAddress(@PathVariable("idaddress") int id) {
        addressService.delete(id);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewProduct(Address address) {

        addressService.saveOrUpdate(address);
        return "Saved";
    }
}
