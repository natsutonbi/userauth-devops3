package com.example.demo.user.service;

import org.springframework.stereotype.Service;

import com.example.demo.user.entity.userAccountInfo;

@Service
public interface userService {
    userAccountInfo genByPwd(String password);//新建账号

    int regist(userAccountInfo info);//注册

    userAccountInfo getInfoByUsername(String username);

    boolean ifexist(String username);//是否存在用户

    boolean check(String username,String password);

    int update(userAccountInfo info);
}
