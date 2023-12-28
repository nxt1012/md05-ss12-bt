package com.ra.smarthome.services;

import com.ra.smarthome.payload.request.user.LoginRequest;
import com.ra.smarthome.payload.request.user.SignupRequest;

public interface AuthService {
    void registerUser(SignupRequest signupRequest);

    String loginUser(LoginRequest loginRequest);
}
