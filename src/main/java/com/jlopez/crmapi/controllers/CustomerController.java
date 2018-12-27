package com.jlopez.crmapi.controllers;

import com.jlopez.crmapi.entities.Customer;
import com.jlopez.crmapi.exceptions.ExceptionHandlerController;
import com.jlopez.crmapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController extends ExceptionHandlerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById(@PathVariable Long customerId) {
        return customerService.findById(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public String create() {
        return customerService.create();
    }
}
