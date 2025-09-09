package com.mederp.controller;

import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mederp.dto.ApiResponse;
import com.mederp.dto.LoginRequest;
import com.mederp.dto.LoginResponse;
import com.mederp.dto.RegistrationRequestDTO;
import com.mederp.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
// @CrossOrigin("http://http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    //Register user (only Super_Admin should be allowed - handle in SecurityConfig)
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register (@Valid @RequestBody RegistrationRequestDTO request) {
        ApiResponse<String> response = authService.register(request);
        return ResponseEntity.ok(response);
    }


    //Login (only ADMIN and Super_ADMIN can log in)
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login (@Valid @RequestBody LoginRequest request) {
        ApiResponse<LoginResponse> response = authService.login(request);
        return ResponseEntity.ok(response);

    }

}
