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

@Controller
@RequestMapping("/v2/user")
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
}
