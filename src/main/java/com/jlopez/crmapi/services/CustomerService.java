package com.jlopez.crmapi.services;

import com.jlopez.crmapi.entities.Customer;
import com.jlopez.crmapi.models.CustomUserDetails;
import com.jlopez.crmapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Optional<Customer> create(Customer customer, CustomUserDetails userDetails) {
        customer.setCreatedBy(userDetails.getId());
        return Optional.of(customerRepository.save(customer));
    }

    public Optional<Customer> update(Customer customer) {
        return Optional.ofNullable(null);
    }

    public void delete(Long customerId) {
        Optional<Customer> customerFromDatabase = findById(customerId);

        if (!customerFromDatabase.isPresent()) {
            throw new EntityNotFoundException(String.format("Customer with id %d is not in the database", customerId));
        }

        Customer customerToSave = customerFromDatabase.get();

        customerToSave.setDeleted(true);

        customerRepository.save(customerToSave);
    }
}
