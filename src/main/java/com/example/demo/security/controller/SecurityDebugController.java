package com.example.demo.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/user/debug")
public class SecurityDebugController {

    @ResponseBody
    @RequestMapping("/whoami")
    public String whoami(Authentication currentAuth){
        if(currentAuth == null)
            return "you are visitor";
        return currentAuth.getName();
    }
}
