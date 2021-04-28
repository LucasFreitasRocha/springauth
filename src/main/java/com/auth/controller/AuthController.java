package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.in.LoginDto;
import com.auth.dto.out.TokenDto;
import com.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired private AuthService authService;
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Validated LoginDto loginDto){
		return ResponseEntity.ok(authService.authenticate(loginDto, authManager));
	}
}
