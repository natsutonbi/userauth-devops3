package com.example.demo.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.security.mapper.entity.Account;
import com.example.demo.security.service.MyUser;
import com.example.demo.security.service.MyUserManager;

import io.swagger.v3.oas.annotations.tags.Tag;


@Controller
@RequestMapping("/api/user")
@Tag(name = "用户基础接口", description = "登录/注册/注销")
public class UserBaseController {

    @Autowired
    MyUserManager manager;

    @Autowired
    PasswordEncoder encoder;

    @ResponseBody
    @RequestMapping("/regist")
    public String regist(@RequestParam("password") String password){
        String username = manager.getNewUsername();
        Account account = new Account()
                                .setUsername(username)
                                .setPassword(encoder.encode(password))
                                .setEnabled(true);
        MyUser user = new MyUser(account, null).addRole("USER");
        manager.createUser(user);
        return username;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam("username") String username){
        manager.deleteUser(username);
        return "success delete user %s".formatted(username);
    }
}
