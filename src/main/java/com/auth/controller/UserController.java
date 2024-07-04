package com.auth.controller;

import com.auth.dto.in.RegisterDto;
import com.auth.dto.out.TokenDto;
import com.auth.dto.out.UserDto;
import com.auth.model.Usuario;
import com.auth.service.TokenService;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<TokenDto> register(@RequestBody RegisterDto registerDto){

        UsernamePasswordAuthenticationToken dadosLogin = registerDto.converter();
        Usuario user = service.create(registerDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.created(uri).body(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> allUsers(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.findAll());
    }

}
