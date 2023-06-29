package com.example.demo.user.utils;

import jakarta.servlet.http.Cookie;

public class cookiesUtil {
    static String get(Cookie[] cookies,String key)
    {
        for(Cookie item:cookies)
        {
            if(key.equals(item.getName()))
                return item.getValue();
        }
        return null;
    }
}
