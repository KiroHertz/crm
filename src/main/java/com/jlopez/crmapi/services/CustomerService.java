package com.jlopez.crmapi.services;

import com.jlopez.crmapi.entities.Customer;
import com.jlopez.crmapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public String create() {
        return "Customer creation";
    }
}
