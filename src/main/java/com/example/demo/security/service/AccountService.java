package com.example.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.security.utils.FlowUtils;

@Service
public class AccountService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FlowUtils flow;
}
