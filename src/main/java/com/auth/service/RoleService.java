package com.auth.service;

import com.auth.model.Role;
import com.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService  {

    @Autowired
    private RoleRepository repository;

    public Role create(String name){
        Role role = new Role();
        role.setName(name);
        return repository.save(role);
    }

}
