package com.purna.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.purna.dto.LoginRequestDTO;
import com.purna.dto.SignUpRequestDTO;
import com.purna.service.AuthServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthServices authServices;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO request) {
	    String token = authServices.login(request);
	    return ResponseEntity.ok(token);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody @Valid SignUpRequestDTO request) {
	    authServices.signup(request);
	    return ResponseEntity.ok("User registered successfully");
	}
}
