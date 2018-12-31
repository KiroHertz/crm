package com.jlopez.crmapi.services;

import com.jlopez.crmapi.entities.User;
import com.jlopez.crmapi.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService
                .findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("[CustomUserDetailService] User with name %s not found", name)));

        return new CustomUserDetails(user);
    }
}
