package com.example.image_manager.controller;

import com.example.image_manager.request.LoginRequest;
import com.example.image_manager.request.RegisterRequest;
import com.example.image_manager.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping("/login")
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER') ")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(authService.login(loginRequest));
	}

	@PostMapping("/register")
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_USER') ")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.ok(authService.register(registerRequest));
	}
}
