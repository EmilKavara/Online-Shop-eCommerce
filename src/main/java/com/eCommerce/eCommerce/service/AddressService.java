package com.eCommerce.eCommerce.service;

import com.eCommerce.eCommerce.dao.AddressRepository;
import com.eCommerce.eCommerce.model.Address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(int id) {
        return addressRepository.findById(id).get();
    }

    public void saveOrUpdate(Address address) {
        addressRepository.save(address);
    }

    public void delete(int id) {
        addressRepository.deleteById(id);
    }

    public void update(Address address, int id) {
        addressRepository.save(address);
    }

}
