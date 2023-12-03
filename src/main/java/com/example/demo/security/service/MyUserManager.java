package com.example.demo.security.service;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.security.mapper.PermissionMapper;
import com.example.demo.security.mapper.RoleMapper;
import com.example.demo.security.mapper.UserMapper;
import com.example.demo.security.mapper.entity.Account;
import com.example.demo.security.mapper.entity.Role;
import com.example.demo.security.utils.SnowFlake;
import com.example.demo.security.mapper.entity.Permission;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


public class MyUserManager {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    PasswordEncoder encoder;

    @Value("${snowflake.user.workId}")
    private static long workId;

    @Value("${snowflake.datacenterId}")
    private static long datacenterId;

    private static SnowFlake usernameGenerator=new SnowFlake(workId,datacenterId);

    public String getNewUsername() {
        return Long.toString(usernameGenerator.nextId());
    }

    @Transactional(rollbackFor = Exception.class)
    public void createUser(MyUser myUser){
        Account account = myUser.getAccount();
        userMapper.insert(account);
        Collection<? extends GrantedAuthority> authorities = myUser.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        while (iter.hasNext()) {
            String auth = iter.next().getAuthority();
            if(auth.startsWith("ROLE_")){
                roleMapper.insert(new Role(account.getUsername(),auth));
            }
            else{
                permissionMapper.insert(new Permission(account.getUsername(),auth));
            }
        }
    }

    public void changePassword(String oldPassword, String newPassword){
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            throw new IllegalArgumentException("旧密码或新密码不能为空");
        }
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if(currentAuth == null|| !currentAuth.isAuthenticated())
            throw new IllegalStateException("用户未登录，无法修改密码");
        Account account = userMapper.selectById(currentAuth.getName());
        if(encoder.matches(oldPassword, account.getPassword())){
            account.setPassword(encoder.encode(newPassword));
            userMapper.updateById(account);
        }else throw new BadCredentialsException("旧密码验证失败");
    }

    public void deleteUser(String username){
        if(!userExists(username)) 
            throw new EmptyResultDataAccessException(1);
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        userMapper.delete(wrapper.eq("username", username));
    }

    public boolean userExists(String username) {
        Account account = userMapper.selectById(username);
        if(account == null){
            return false;
        }
        return true;
    }
    
}
