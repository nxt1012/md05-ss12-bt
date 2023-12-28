package com.ra.smarthome.services;

import com.ra.smarthome.models.User;
import com.ra.smarthome.payload.request.user.LoginRequest;
import com.ra.smarthome.payload.request.user.SignupRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User createUser(User newUser);
    User updateUser(Long userId, User updateUser);
    void deleteUser(Long userId);
}
