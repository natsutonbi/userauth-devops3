package com.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.example.demo.security.filter.addSaltFilter;
import com.example.demo.security.utils.Md5PasswordEncoder;

@EnableWebSecurity
public class securityConfig{
    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return new Md5PasswordEncoder();
    // }

    @Bean
    public SecurityFilterChain config(HttpSecurity http)throws Exception{
        http
        .authorizeHttpRequests().anyRequest().permitAll()
            // .requestMatchers("/").authenticated()
            ;
        // http.addFilterAt(addSalt(http),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // @Bean
    // public addSaltFilter addSalt(HttpSecurity http)throws Exception{
    //     addSaltFilter saltFilter=new addSaltFilter();
    //     saltFilter.setFilterProcessesUrl("/login");
    //     return saltFilter;
    // }
}
