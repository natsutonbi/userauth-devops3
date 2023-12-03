package com.example.demo.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.security.mapper.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyUser implements UserDetails,CredentialsContainer{

    private Account account;
    private Collection<SimpleGrantedAuthority> authorities;

    public MyUser addAuthority(String... authorities){
        if (this.authorities == null){
            this.authorities = new ArrayList<>();
        }
        for(String authority:authorities){
            this.authorities.add(new SimpleGrantedAuthority(authority));
        }
        return this;
    }

    public MyUser addRole(String... roles){
        if (this.authorities == null){
            this.authorities = new ArrayList<>();
        }
        for(String role:roles){
            if(role.startsWith("ROLE_"))
                throw new IllegalArgumentException("角色名不能以 'ROLE_' 前缀开头, 系统会自动添加");
            this.authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; 
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }

    @Override
    public void eraseCredentials() {//清除密码，防止二次利用
        account.setPassword(null);
    }
    
}
