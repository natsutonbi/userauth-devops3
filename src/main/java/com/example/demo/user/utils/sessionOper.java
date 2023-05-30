package com.example.demo.user.utils;

import jakarta.servlet.http.HttpSession;

public class sessionOper {
    public static String getUsername(HttpSession session){
        Object loginUser=session.getAttribute("loginUser");
        if(loginUser==null) return "";
        return loginUser.toString();
    }
}
