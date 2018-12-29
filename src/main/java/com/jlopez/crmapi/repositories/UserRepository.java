package com.jlopez.crmapi.repositories;

import com.jlopez.crmapi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByNameAndDeletedFalse(String email);
}
