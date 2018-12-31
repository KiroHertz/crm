package com.jlopez.crmapi.services;

import com.jlopez.crmapi.entities.Customer;
import com.jlopez.crmapi.models.CustomUserDetails;
import com.jlopez.crmapi.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final StorageService storageService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, StorageService storageService) {
        this.customerRepository = customerRepository;
        this.storageService = storageService;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Optional<Customer> create(Customer customer, CustomUserDetails customUserDetails) {
        customer.setCreatedBy(customUserDetails.getId());
        return Optional.of(customerRepository.save(customer));
    }

    public Optional<Customer> update(Long customerId, Customer customer, CustomUserDetails customUserDetails) {
        Customer customerToSave = getCustomerFromDatabase(customerId);
        return Optional.ofNullable(customer);
    }

    public void delete(Long customerId) {
        Customer customerToSave = getCustomerFromDatabase(customerId);

        customerToSave.setDeleted(true);

        customerRepository.save(customerToSave);
    }

    public void saveCustomerPhoto(Long customerId, MultipartFile file, CustomUserDetails customUserDetails) {
        Customer customerToSave = getCustomerFromDatabase(customerId);

        if (customerToSave.getPhotoUrl() != null && !customerToSave.getPhotoUrl().isEmpty()) {
            storageService.delete(customerId, file.getOriginalFilename());
        }

        storageService.store(customerId, file.getOriginalFilename(), file);

        customerToSave.setPhotoUrl(file.getOriginalFilename());
        customerToSave.setUpdatedBy(customUserDetails.getId());

        customerRepository.save(customerToSave);

    }

    private Customer getCustomerFromDatabase(Long customerId) throws EntityNotFoundException {
        Optional<Customer> customerFromDatabase = findById(customerId);

        if (!customerFromDatabase.isPresent()) {
            throw new EntityNotFoundException(String.format("Customer with id %d is not in the database", customerId));
        }
        return customerFromDatabase.get();
    }
}
