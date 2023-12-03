package com.example.demo.security.config;

import java.util.Arrays;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.JdbcUserDetailsManager;
// import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.example.demo.security.service.AuthorizeService;
import com.example.demo.security.service.MyUserManager;

// import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    MyUserManager manager(){
        return new MyUserManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // private AuthenticationManager authenticationManager(UserDetailsManager manager,
    //         PasswordEncoder encoder) {
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setUserDetailsService(manager);
    //     provider.setPasswordEncoder(encoder);
    //     return new ProviderManager(provider);
    // }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource,
            PasswordEncoder encoder) {
        // JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        // // 仅首次启动时创建一个新的用户用于测试，后续无需创建
        // if (!manager.userExists("user")) {
        //     manager.createUser(
        //         User
        //             .withUsername("user")
        //             .password(encoder.encode("password"))
        //             .roles("USER").build());
        // }
        // manager.setAuthenticationManager(authenticationManager(manager, encoder));
        // return manager;
        return new AuthorizeService();
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http, UserDetailsService userDetailsService, PersistentTokenRepository repository) throws Exception {
        http
            .csrf(conf -> {
                conf.disable(); // 直接关闭全部的csrf校验
            })
            .formLogin(form -> form
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login/val")
                .defaultSuccessUrl("/")
                .permitAll())
            .rememberMe(
                conf -> conf
                    .alwaysRemember(false)
                    .rememberMeParameter("remember-me")
                    .rememberMeCookieName("auth-token")
                    .userDetailsService(userDetailsService)
                    .tokenRepository(repository) //设置记住我持久化存储库
                    .tokenValiditySeconds(3600 * 7)   //设置记住我有效时间为7天
                )
            .logout(conf -> {
                conf.logoutUrl("/user/logout"); // 退出登录地址，跟上面一样可自定义
                conf.logoutSuccessUrl("/user/login"); // 退出登录成功后跳转的地址，这里设置为登录界面
                conf.permitAll();
            })
            .passwordManagement(management -> management
                    .changePasswordPage("/user/manage/password/update"))
            .authorizeHttpRequests(authorize -> {
                authorize
                    .requestMatchers("/user/whoami").permitAll()
                    .requestMatchers("/user/login/val").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/user/login").permitAll()
                    .requestMatchers("/user/**").authenticated()
                    .requestMatchers("/static/**").permitAll() // 将所有的静态资源放行，一定要添加在全部请求拦截之前
                    .anyRequest().permitAll();
                // .anyRequest().authenticated();//拦截剩余
            });
        http.securityContext(context -> context.securityContextRepository(securityContextRepository()));
        return http.build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        //在启动时自动在数据库中创建存储记住我信息的表，仅第一次需要，后续不需要
        // repository.setCreateTableOnStartup(true);
        repository.setDataSource(dataSource);
        return repository;
    }

    @Bean
    AuthenticationManager authenticationManager(PasswordEncoder encoder, UserDetailsService userDetailsService)
            throws Exception {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(encoder);
        dao.setUserDetailsService(userDetailsService);
        return new ProviderManager(Arrays.asList(dao));
    }

    @Bean
    public HttpSessionSecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}
