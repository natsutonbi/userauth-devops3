package com.example.demo.security.mapper.entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Authority implements GrantedAuthority {

    public String property;

    @Override
    public String getAuthority() {
        return property;
    }
    
}
