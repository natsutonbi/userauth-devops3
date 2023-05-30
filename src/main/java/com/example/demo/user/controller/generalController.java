package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.user.service.userService;

import jakarta.servlet.http.HttpSession;

@Controller
public class generalController {
    @Autowired
    userService userv;

    @ResponseBody//直接写入response，不mapper
    @RequestMapping("/user/whoami")
    public String whoami(HttpSession session)
    {
        Object username=session.getAttribute("loginUser");
        if(username==null) return "you are visitor";
        String account=username.toString();
        String nickname=userv.getInfoByUsername(account).nickname;
        return "you are "+nickname+". account="+account;
    }
    @RequestMapping("/user/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:/user/login";
    }
}
