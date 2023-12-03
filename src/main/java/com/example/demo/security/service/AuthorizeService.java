package com.example.demo.security.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.security.mapper.UserMapper;
import com.example.demo.security.mapper.entity.Account;

public class AuthorizeService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userMapper.selectById(username);
        Set<SimpleGrantedAuthority> authorities = userMapper.getPermission(username);
        authorities.addAll(userMapper.getRoles(username));
        return new MyUser(account,authorities);
    }
    
}
