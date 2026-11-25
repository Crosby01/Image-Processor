
package com.example.image.controller;

import com.example.image.dto.LoginRequest;
import com.example.image.dto.LoginResponse;
import com.example.image.dto.RegisterRequest;
import com.example.image.dto.UserResponse;
import com.example.image.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
public ResponseEntity<String> getCurrentUser(
        org.springframework.security.core.Authentication authentication) {

    return ResponseEntity.ok(
            "Authenticated as: " + authentication.getName()
    );
}
}


