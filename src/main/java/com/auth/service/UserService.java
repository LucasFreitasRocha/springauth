package com.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.exception.ObjectNotFoundException;
import com.auth.model.Usuario;
import com.auth.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired private TokenService tokenService;
	@Autowired private UserRepository repo;

	public Usuario findByToken(String token) {
		 
		return find(tokenService.getIdUser(token));
	}

	private Usuario find(Long id) {
		  Optional<Usuario> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"User não encontrado com esse id: " + id));
	}

	
	
}
