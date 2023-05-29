package com.example.demo.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.demo.entity.userAccountInfo;

@Service
public interface userService {
    userAccountInfo genByPwd(String password);

    int regist(@Param("info") userAccountInfo info);

    userAccountInfo getByUsername(String username);

    boolean ifexist(String username);//是否存在用户

    boolean check(String username,String password);
}
