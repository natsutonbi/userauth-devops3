package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.user.entity.msgReciever;
import com.example.demo.user.entity.userAccountInfo;
import com.example.demo.user.service.userManageService;
import com.example.demo.user.service.userService;
import com.example.demo.user.utils.MD5SaltPwd;
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
        @RequestParam(name = "emailuser",defaultValue = "")String emailuser,
        @RequestParam(name = "emaildomain",defaultValue = "")String emaildomain,
        @RequestParam(name = "tel",defaultValue = "")String tel,
        HttpSession session)
    {
        Object loginUser=session.getAttribute("loginUser");
        if(loginUser==null) return "wrong user";
        String username=loginUser.toString();
        msgReciever reciever=uManageServ.getRecieverByUsername(username);
        String email="";
        if(reciever==null){
            if(!uServ.ifexist(username)){
                log.error("no such user: {}, but still pass login intercreptor",username);
                return "you are not in the system";
            }
            reciever=new msgReciever(username,email,tel);
            uManageServ.initReciever(reciever);//没有则初始化
        }
        boolean changeFlag=false;
        if(!"".equals(emailuser))
        {
            email=emailuser+"@"+emaildomain;
            reciever.setEmail(email);
            log.info("{} set email={}", username, email);
            changeFlag=true;
        }
        if(!"".equals(tel)) {
            reciever.setTel(tel);
            log.info("{} set tel={}", username, tel);
            changeFlag=true;
        }
        if(changeFlag){
            uManageServ.updateReciever(reciever);
            return "set success";
        }
        return "nothing happened, plz input email/tel";
    }

    @ResponseBody
    @RequestMapping("/set/info")
    public String setInfo(
        @RequestParam(name = "password",defaultValue = "") String pwd,
        @RequestParam(name = "nickname",defaultValue = "") String nickname,
        HttpSession session
    ){
        userAccountInfo info=uServ.getInfoByUsername(sessionOper.getUsername(session));
        if(info==null) return "wrong user";
        if(!("".equals(pwd)||pwd==null)) info.password=MD5SaltPwd.getSaltedPwd(pwd, info.salt);
        if(!("".equals(nickname)||nickname==null)) info.nickname=nickname;
        uServ.update(info);
        return "success";
    }
}
