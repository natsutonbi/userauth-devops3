package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.user.entity.userAccountInfo;
import com.example.demo.user.service.userService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.ModelMap;
@Slf4j
@Controller
public class LoginController 
{
    @Autowired
    private userService userv;

    //127.0.0.1:8080/user/login/val?username=3621404104720384&password=123456
    @RequestMapping("/user/login/val")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
        HttpServletRequest request,
        HttpServletResponse response,
        ModelMap map,
        HttpSession session)
    {
        if(userv.check(username, password)){
            //登录成功
            userAccountInfo info=userv.getInfoByUsername(username);
            Cookie ck_usr=new Cookie("isLogin", "true");
            Cookie ck_name=new Cookie("username", username);
            ck_usr.setMaxAge(15*24*60*60);//15day
            ck_usr.setPath("/");
            ck_name.setMaxAge(15*24*60*60);//15day
            ck_name.setPath("/");
            response.addCookie(ck_usr);
            response.addCookie(ck_name);
            session.setAttribute("loginUser", username);
            log.info("user ["+username+"] login");
            map.put("msg", "登录成功, 欢迎"+info.nickname);
            return "success";
        }
        else{
            log.warn("user [{}] tends to login but failed authentication. password:{}",username,password);
            map.put("msg", "用户名密码错误");
            map.addAttribute("pswd",password);
            map.addAttribute("usrname",username);
            return "login_show";
        }
    }
    @GetMapping(value="/user/login")
    public String login_form(
        ModelMap map,
        HttpSession session)
    {
        return "loginForm";
    }
}