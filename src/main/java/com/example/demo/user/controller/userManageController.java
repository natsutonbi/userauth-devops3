package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.user.entity.msgReciever;
import com.example.demo.user.service.userManageService;
import com.example.demo.user.service.userService;
import com.example.demo.user.utils.sessionOper;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/user/manage")
@Controller
public class userManageController {
    @Autowired
    userManageService uManageServ;

    @Autowired
    userService uServ;

    @ResponseBody
    @RequestMapping("/get/reciever")
    public String getReciever(HttpSession session)
    {
        String username=sessionOper.getUsername(session);
        return uManageServ.getRecieverByUsername(username).toString();
    }

    @ResponseBody
    @RequestMapping("/set/reciever")
    public String setReciever(
        @RequestParam(name = "email",defaultValue = "")String email,
        @RequestParam(name = "tel",defaultValue = "")String tel,
        HttpSession session)
    {
        Object loginUser=session.getAttribute("loginUser");
        if(loginUser==null) return "wrong user";
        String username=loginUser.toString();
        msgReciever reciever=uManageServ.getRecieverByUsername(username);
        if(reciever==null){
            if(!uServ.ifexist(username)){
                log.error("no such user: {}, but still pass login intercreptor",username);
                return "you are not in the system";
            }
            reciever=new msgReciever(username,email,tel);
            uManageServ.initReciever(reciever);
            return "set success";
        }
        if(!"".equals(email)) reciever.setEmail(email);
        if(!"".equals(tel)) reciever.setTel(tel);
        uManageServ.updateReciever(reciever);
        return "set success";
    }
}
