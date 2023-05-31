package com.example.demo.user.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.user.intercreptor.LoginHandlerInterceptor;

//后续改配置文件导入
@Configuration
public class loginConfig implements WebMvcConfigurer {

    //注册连接器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/regist");
    }
}
