package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.user.entity.userAccountInfo;
import com.example.demo.user.service.mailService;
import com.example.demo.user.service.userManageService;
import com.example.demo.user.service.userService;
import com.example.demo.user.utils.sessionOper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/message")
public class messageController {
    @Autowired
    mailService mail;

    @Autowired
    userService uServ;

    @Autowired
    userManageService uMServ;

    @ResponseBody
    @RequestMapping("/send/simplemail")
    public String sendEmail(
        @RequestParam("content") String content,
        @RequestParam("target") String targetUsername,
        @RequestParam(name = "topic",defaultValue = "no topic") String topic,
        HttpSession session)
    {
        userAccountInfo info=uServ.getInfoByUsername(sessionOper.getUsername(session));
        String prefix="message from "+info.nickname+"("+info.username+"):\n";
        if(!uServ.ifexist(targetUsername)) return "no such user, username: "+targetUsername;
        String tarAddr=uMServ.getRecieverByUsername(targetUsername).email;
        try{
            mail.sendSimpleMail(tarAddr, topic, prefix+content);
        }
        catch(Exception e){
            return "failed to send mail";
        }
        return "send email success";
    }
}
