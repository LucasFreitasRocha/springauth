package com.auth.service;

import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.dto.in.LoginDto;
import com.auth.dto.out.TokenDto;
import com.auth.model.Usuario;
import com.auth.repository.UserRepository;

@Service
@AllArgsConstructor
public class AuthService {
	private final TokenService tokenService;
	private final  AuthenticationManager authManager;

	public TokenDto authenticate(LoginDto loginDto) {
		UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();
		Authentication authentication = authManager.authenticate(dadosLogin);
		return new TokenDto(tokenService.gerarToken(authentication), "Bearer");
		
		
	}

}


