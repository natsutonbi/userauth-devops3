package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.userAccountInfo;
import com.example.demo.service.userService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.ModelMap;

@Slf4j
@Controller
public class registController {
    @Autowired
    userService userv;

    @RequestMapping("/user/regist")
    public String regist(
        @RequestParam("password")String password,
        @RequestParam("nickname")String nickname,
        ModelMap map)
    {
        userAccountInfo info;
        try{
            info=userv.genByPwd(password);
        }catch(Exception e){
            log.error("generate user by password error: "+e);
            map.put("msg", "生成账号失败");
            return "success";
        }
        log.info("生成账号 {}, nickname: {} password: {} createtime: {}", info.username, nickname, password, info.createtime);
        if(nickname==null) info.nickname="";
        else info.nickname=nickname;
        int code;
        try {
            code=userv.regist(info);
        } catch (Exception e) {
            log.error("regist to database erro: "+e);
            map.put("msg", "向数据库注册失败");
            return "success";
        }
        log.info("regist code: {}",code);
        map.put("msg", "注册成功,账号: "+info.username);
        return "success";
    }
}
