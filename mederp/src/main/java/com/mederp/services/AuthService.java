package com.mederp.services;

import java.time.LocalDateTime;

// import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mederp.dto.ApiResponse;
import com.mederp.dto.LoginRequest;
import com.mederp.dto.LoginResponse;
import com.mederp.dto.RegistrationRequestDTO;
import com.mederp.entities.User;
import com.mederp.enums.Role;
import com.mederp.repository.UserRepository;
import com.mederp.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public ApiResponse<String> register (RegistrationRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ApiResponse.<String>builder()
            .success(false)
            .message("Email Already exists")
            .build();
        }

        User user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .flag(true)
            .build();

        userRepository.save(user);

        return ApiResponse.<String>builder()
            .success(true)
            .message("User registered successfully.")
            .timestamp(LocalDateTime.now())
            .data(user.getEmail())
            .build();
    }

    public ApiResponse<LoginResponse> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid Credentials."));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials.");
        }

        //only allow ADMIN and SUPER_ADMIN
        if (user.getRole() == Role.USER) {
            return ApiResponse.<LoginResponse>builder()
                .success(false)
                .timestamp(LocalDateTime.now())
                .message("Access denied for USER Role")
                .build();
            
        }

        //if user email is not present then
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return ApiResponse.<LoginResponse>builder()
            .success(false)
            .timestamp(LocalDateTime.now())
            .message("User email is not present.")
            .build();
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        LoginResponse loginResponse = LoginResponse.builder()
            .token(token)
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .build();

        return ApiResponse.<LoginResponse>builder()
            .success(true)
            .message("Login Successful.")
            .timestamp(LocalDateTime.now())
            .data(loginResponse)
            .build();
        }

}
