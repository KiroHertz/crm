package com.jlopez.crmapi.services;

import com.jlopez.crmapi.entities.User;
import com.jlopez.crmapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByNameAndDeletedFalse(String name) {
        return userRepository.findByNameAndDeletedFalse(name);
    }

    public Optional<User> create(User user) {
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> update(Long userId, User user) {
        User userToSave = getUserFromDatabase(userId);
        return Optional.of(userRepository.save(user));
    }

    public void changeAdminStatus(Long userId, boolean administratorStatus) {
        User userToSave = getUserFromDatabase(userId);

        userToSave.setAdmin(administratorStatus);

        userRepository.save(userToSave);
    }

    public void delete(Long userId) {
        User userToSave = getUserFromDatabase(userId);

        userToSave.setDeleted(true);

        userRepository.save(userToSave);
    }

    private User getUserFromDatabase(Long userId) throws EntityNotFoundException {
        Optional<User> userFromDatabase = findById(userId);

        if (!userFromDatabase.isPresent()) {
            throw new EntityNotFoundException(String.format("User with id %d is not in the database", userId));
        }
        return userFromDatabase.get();
    }
}
