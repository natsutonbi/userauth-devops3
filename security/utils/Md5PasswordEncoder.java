package com.example.demo.security.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.user.utils.MD5SaltPwd;

public class Md5PasswordEncoder implements PasswordEncoder{

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5SaltPwd.md5Hex(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String pwdFromDB=MD5SaltPwd.md5Hex(rawPassword.toString());
        return pwdFromDB.equals(encodedPassword);
    }
}
