package com.example.CarSalesMng.services;

import com.example.CarSalesMng.data.CustomerRepository;
import com.example.CarSalesMng.models.Customer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer add(Customer newCustomer) {
        if(newCustomer == null) {
            throw new NotImplementedException();
        }
        return customerRepository.save(newCustomer);
    }

    public Customer update(Customer updatedCustomer) {
        if(updatedCustomer == null) {
            throw new NotImplementedException();
        }
        return customerRepository.save(updatedCustomer);
    }
}
