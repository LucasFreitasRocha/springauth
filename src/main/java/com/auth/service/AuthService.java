package com.auth.service;

import java.util.Optional;

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
import com.auth.model.User;
import com.auth.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	

	
	@Autowired
	private TokenService tokenService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> usuario = userRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		
		throw new UsernameNotFoundException("bad credentials!");
	}


	public TokenDto authenticate(LoginDto loginDto, AuthenticationManager authManager) {
		UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();
		Authentication authentication = authManager.authenticate(dadosLogin);
		String token = tokenService.gerarToken(authentication);
		return new TokenDto(token, "Bearer");
		
		
	}

}


