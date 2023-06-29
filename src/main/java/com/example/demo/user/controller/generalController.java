package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.user.service.userService;
import com.example.demo.user.utils.sessionOper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class generalController {
    @Autowired
    userService userv;

    @ResponseBody//直接写入response，不mapper
    @RequestMapping("/user/whoami")
    public String whoami(HttpServletRequest request,HttpSession session)
    {
        Object username=session.getAttribute("loginUser");
        Cookie[] cookies=request.getCookies();
        for(Cookie item:cookies){
            if(item.getName().equals("username"))
                username=item.getValue();
        }
        if(username==null) return "you are visitor";
        String account=username.toString();
        String nickname=userv.getInfoByUsername(account).nickname;
        return "you are "+nickname+".\naccount= "+account;
    }
    @ResponseBody
    @RequestMapping("/user/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus,HttpServletResponse response){
        String username=sessionOper.getUsername(session);
        log.info("user [{}] wanna logout",username);
        session.invalidate();
        sessionStatus.setComplete();
        log.info("user [{}] session over",username);
        Cookie cookie=new Cookie("isLogin", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        log.info("user [{}] cookie over",username);
        return "["+username+"] logout success";
    }
    @ResponseBody
    @RequestMapping("/user/nosession")
    public String sessionOver(HttpSession session, SessionStatus sessionStatus,HttpServletResponse response){
        String username=sessionOper.getUsername(session);
        log.info("user [{}] wanna clear session",username);
        session.invalidate();
        sessionStatus.setComplete();
        log.info("user [{}] session over",username);
        return "["+username+"] clear session success";
    }
    @ResponseBody
    @RequestMapping("/user/sessioncheck")
    public String sessioncheck(HttpSession session, SessionStatus sessionStatus,HttpServletResponse response){
        String username=sessionOper.getUsername(session);
        log.info("user [{}] wanna check session",username);
        return "the session shows you are ["+username+"]";
    }
}
