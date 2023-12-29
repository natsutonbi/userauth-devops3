package com.example.demo.mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.security.service.MailService;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//随机端口启动
// @EnableWebMvc
// @RunWith(SpringRunner.class)
public class mailTest {
    @Autowired
    MailService mailService;

    // @Test
    // public void getCode(){
    //     mailService.generateEmailVerifyCode("1306512118@qq.com", "127.0.0.1");
    // }

    // @Test
    // public void verifyCode(){
    //     assert mailService.checkEmailVerifyCode("1306512118@qq.com", "787616");
    // }

    // @Test
    // public void delCode(){
    //     mailService.delEmailVerifyCode("1306512118@qq.com");
    // }
}
