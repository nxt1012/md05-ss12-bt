package com.ra.smarthome.services;

import com.ra.smarthome.exceptions.DuplicateEmailException;
import com.ra.smarthome.exceptions.DuplicatePhoneException;
import com.ra.smarthome.exceptions.UserAlreadyExistsException;
import com.ra.smarthome.models.User;
import com.ra.smarthome.models.enums.ERole;
import com.ra.smarthome.payload.request.user.LoginRequest;
import com.ra.smarthome.payload.request.user.SignupRequest;
import com.ra.smarthome.repositories.UserRepository;
import com.ra.smarthome.security.jwt.JwtUtils;
import com.ra.smarthome.security.userdetails.UserDetailsImpl;
import com.ra.smarthome.security.userdetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public void registerUser(SignupRequest signupRequest) {
//        Check for duplicate username
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new UserAlreadyExistsException("Username is already taken!");
        }
//        Check for duplicate email
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new DuplicateEmailException("Email is already in use");
        }
//        Check for duplicate phone
        if (userRepository.existsByPhone(signupRequest.getPhone())) {
            throw new DuplicatePhoneException("Phone number is already in use");
        }
        // Create a new user account
        User user = User.builder()
                .username(signupRequest.getUsername())
                .fullName(signupRequest.getFullName())
                .email(signupRequest.getEmail())
                .phone(signupRequest.getPhone())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(ERole.ROLE_USER)
                .build();

        userRepository.save(user);

        // Authenticate the new user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signupRequest.getUsername(), signupRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token and set it in the response cookie
//        FIXME: avoid casting here, redesign Jwt as needed
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(signupRequest.getUsername());
        jwtUtils.generateCookie(userDetails);
    }


    @Override
    public String loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        return jwtUtils.generateTokenFromUsername(userDetails.getUsername());    }
}
