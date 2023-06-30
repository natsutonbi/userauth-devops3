package com.example.demo.security;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.security.utils.Md5PasswordEncoder;
import com.example.demo.user.utils.MD5SaltPwd;

@SpringBootTest
public class encoderTest {
    @Test
    public void TestMd5Encoder()throws Exception{
        Md5PasswordEncoder md5Encoder=new Md5PasswordEncoder();
        String pwd="pas";
        String salt=MD5SaltPwd.getSalt();
        String expected=MD5SaltPwd.getSaltedPwd(pwd, salt);
        if(md5Encoder.matches((pwd+salt), expected)) return;
        else throw new Exception("不相等");
    }
}
