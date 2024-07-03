package com.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;



@Entity
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String name;


    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = "ROLE_" + name.toUpperCase();
    }


    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.name;
    }

}
