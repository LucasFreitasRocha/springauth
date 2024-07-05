package com.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.auth.dto.in.RegisterDto;
import com.auth.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.exception.ObjectNotFoundException;
import com.auth.model.Usuario;
import com.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired private TokenService tokenService;
	@Autowired private UserRepository repo;
	@Autowired private RoleService roleService;

	public Usuario findByToken(String token) {
		 
		return find(tokenService.getIdUser(token));
	}

	private Usuario find(Long id) {
		  Optional<Usuario> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"User não encontrado com esse id: " + id));
	}

	public Usuario create(RegisterDto registerDto) {
		Usuario u = new Usuario(registerDto);

		if(registerDto.getModerador()){
			Role role = roleService.create("moderador");
			List<Role> roles = new ArrayList<>();
			roles.add(role);
			u.setRoles(roles);
		}
		BCryptPasswordEncoder hashPassowrd = new BCryptPasswordEncoder();
		u.setPassword(hashPassowrd.encode(registerDto.getPassword()));
		return repo.save(u);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repo.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}

		throw new UsernameNotFoundException("bad credentials!");
	}
	public List<Usuario> findAll() {
		return repo.findAll();
	}
}
