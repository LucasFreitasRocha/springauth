package com.auth.controller;

import com.auth.dto.in.LoginDto;
import com.auth.dto.out.TokenDto;
import com.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

	private final AuthService authService;


	
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Validated LoginDto loginDto){
		return ResponseEntity.ok(authService.authenticate(loginDto));
	}
}
