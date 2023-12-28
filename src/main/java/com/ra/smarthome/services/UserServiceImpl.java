package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.ResourceNotFoundException;
import com.ra.smarthome.models.User;
import com.ra.smarthome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User createUser(User newUser) {
        // TODO: Add validation or business logic
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(Long userId, User updateUser) {
        // Check if the user exists before updating
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        // TODO: Update user properties
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        // Check if the user exists before deleting
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.deleteById(userId);
    }
}
