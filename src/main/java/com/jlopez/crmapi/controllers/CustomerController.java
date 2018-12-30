package com.jlopez.crmapi.controllers;

import com.jlopez.crmapi.entities.Customer;
import com.jlopez.crmapi.exceptions.ExceptionHandlerController;
import com.jlopez.crmapi.models.CustomUserDetails;
import com.jlopez.crmapi.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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
    public ResponseEntity<Customer> create(@Valid @NotNull @RequestBody Customer customer, Authentication authentication) {
        return customerService.create(customer, (CustomUserDetails) authentication.getPrincipal())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping
    public ResponseEntity<Customer> update(@Valid @NotNull @RequestBody Customer customer, Authentication authentication) {
        return customerService.update(customer, (CustomUserDetails) authentication.getPrincipal())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(Long customerId) {
        customerService.delete(customerId);
    }

    @PostMapping("/{id}/photo")
    @ResponseStatus(value = HttpStatus.OK)
    public void handleFileUpload(@PathVariable("id") Long customerId, @RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
        customerService.saveCustomerPhoto(customerId, file, (CustomUserDetails) authentication.getPrincipal());
    }
}
