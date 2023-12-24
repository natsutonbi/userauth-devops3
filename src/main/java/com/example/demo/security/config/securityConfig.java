package com.example.demo.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.filter.JwtAuthorizeFilter;
import com.example.demo.security.service.MyUserManager;
import com.example.demo.security.utils.JwtUtils;
import com.example.demo.utils.RestBean;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
// @ComponentScans({
//     @ComponentScan("com.example.demo.security.controller"),
//     @ComponentScan("com.example.demo.security.service")
// })
// @MapperScan("com.example.demo.security.mapper")
public class securityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserManager();
    }

    @Bean
    AuthenticationManager authenticationManager(PasswordEncoder encoder, UserDetailsService userDetailsService)
            throws Exception {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(encoder);
        dao.setUserDetailsService(userDetailsService);
        return new ProviderManager(Arrays.asList(dao));
    }
    
    @Value("${frontend.address}")
    String frontendAddress;

    @Resource
    JwtAuthorizeFilter jwtAuthorizeFilter;
    
    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> {
                authorize
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/user/whoami").permitAll()
                    .requestMatchers("/user/login/val").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/user/login").permitAll()
                    .requestMatchers("/user/**").authenticated()
                    .requestMatchers("/static/**").permitAll() // 将所有的静态资源放行，一定要添加在全部请求拦截之前
                    .anyRequest().authenticated(); //拦截剩余
                    // .anyRequest().permitAll();
            })
            .formLogin(conf -> conf
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure) // 自定义成功失败返回数据
                .permitAll())
            .logout(conf -> conf
                    .logoutUrl("/api/auth/logout")
                    .logoutSuccessHandler(this::onLogoutSuccess)
            )
            .cors(conf -> {
                    CorsConfiguration cors = new CorsConfiguration();
                  	//添加前端站点地址，这样就可以告诉浏览器信任了
                  	cors.addAllowedOrigin(frontendAddress);
                    //虽然也可以像这样允许所有 cors.addAllowedOriginPattern("*");
                  	//但是这样并不安全，我们应该只许可给我们信任的站点
                    cors.setAllowCredentials(true);  //允许跨域请求中携带Cookie
                    cors.addAllowedHeader("*");   //其他的也可以配置，为了方便这里就 * 了
                    cors.addAllowedMethod("*");
                    cors.addExposedHeader("*");
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);  //直接针对于所有地址生效
                    conf.configurationSource(source);
                })
            .exceptionHandling(conf -> conf
                    .authenticationEntryPoint(this::onUnauthorized))   
            .csrf(conf -> conf.disable()) // 直接关闭全部的csrf校验
            .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class);
            
        //     .rememberMe(
        //         conf -> conf
        //             .alwaysRemember(false)
        //             .rememberMeParameter("remember-me")
        //             .rememberMeCookieName("auth-token")
        //             .userDetailsService(userDetailsService)
        //             .tokenRepository(repository) //设置记住我持久化存储库
        //             .tokenValiditySeconds(3600 * 7)   //设置记住我有效时间为7天
        //         )
        //     .passwordManagement(management -> management
        //             .changePasswordPage("/user/manage/password/update"));
        // http.securityContext(context -> context.securityContextRepository(securityContextRepository()));
        return http.build();
    }

    void onUnauthorized(HttpServletRequest request,
                                 HttpServletResponse response,
                                 AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(RestBean.unauthorized(exception.getMessage()).asJsonString());
    }

    void onAuthenticationFailure(HttpServletRequest request,
                                 HttpServletResponse response,
                                 AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(RestBean.failure(401, exception.getMessage()).asJsonString());
    }

    @Resource
    JwtUtils jwtUtils;

    void onAuthenticationSuccess(HttpServletRequest request, 
                                 HttpServletResponse response, 
                                 Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String token = jwtUtils.createJwt((UserDetails)authentication.getPrincipal());
        PrintWriter writer = response.getWriter();
        writer.write(RestBean.success(token).asJsonString());
    }

    void onLogoutSuccess(HttpServletRequest request, 
                                 HttpServletResponse response, 
                                 Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //TODO
        User user = (User) authentication.getPrincipal();
        String token = jwtUtils.createJwt(user);
        PrintWriter writer = response.getWriter();
        writer.write(RestBean.success(token).asJsonString());
    }
}
