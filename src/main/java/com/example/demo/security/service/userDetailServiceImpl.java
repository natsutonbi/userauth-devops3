package com.example.demo.security.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.security.entity.loginUser;
import com.example.demo.user.dao.loginUserMapper;
import com.example.demo.user.dao.entity.userAccountInfo;

// @Service//查询密码成功
// public class userDetailServiceImpl implements UserDetailsService {
//     @Autowired
//     private loginUserMapper userMapper;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//         //查询用户信息
//         //TODO 查询用户权限
//         //
//         List<userAccountInfo> infos=userMapper.getByUsername(username);
//         if(Objects.isNull(infos))
//             throw new RuntimeException("未找到用户");
//         userAccountInfo info=infos.get(0);

//         //封装UserDetails
//         return new loginUser(info);
//     }
// }
