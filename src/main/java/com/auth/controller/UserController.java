package com.auth.controller;

import com.auth.dto.in.RegisterDto;
import com.auth.dto.out.TokenDto;
import com.auth.model.Usuario;
import com.auth.service.TokenService;
import com.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@AllArgsConstructor
public class UserController {


    private final UserService service;

    private final TokenService tokenService;

    private final  AuthenticationManager authManager;

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
    @Operation(summary = "show all users",security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<List<Usuario>> allUsers(@Parameter(hidden = true) @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.findAll());
    }

}
