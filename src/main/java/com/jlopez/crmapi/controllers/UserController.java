package com.jlopez.crmapi.controllers;

import com.jlopez.crmapi.entities.User;
import com.jlopez.crmapi.exceptions.ExceptionHandlerController;
import com.jlopez.crmapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
