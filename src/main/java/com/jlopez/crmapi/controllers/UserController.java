package com.jlopez.crmapi.controllers;

import com.jlopez.crmapi.entities.User;
import com.jlopez.crmapi.exceptions.ExceptionHandlerController;
import com.jlopez.crmapi.models.UserCreationRequest;
import com.jlopez.crmapi.models.UserUpdateRequest;
import com.jlopez.crmapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends ExceptionHandlerController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<User> create(@Valid @NotNull @RequestBody UserCreationRequest creationRequest) {
        return userService.create(creationRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, UserUpdateRequest updateRequest) {
        return userService.update(userId, updateRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(Long userId) {
        userService.delete(userId);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{userId}/admin")
    @ResponseStatus(value = HttpStatus.OK)
    public void changeAdminStatus(@PathVariable("userId") Long userId, @RequestParam("status") boolean administratorStatus) {
        userService.changeAdminStatus(userId, administratorStatus);
    }

}
