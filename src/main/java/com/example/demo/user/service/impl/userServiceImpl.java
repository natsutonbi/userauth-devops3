package com.example.demo.user.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.user.dao.loginUserMapper;
import com.example.demo.user.entity.userAccountInfo;
import com.example.demo.user.service.userService;
import com.example.demo.user.utils.MD5SaltPwd;
import com.example.demo.user.utils.SnowFlake;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Configuration
public class userServiceImpl implements userService {
    @Autowired
    private loginUserMapper loginmapper;

    @Value("${snowflake.workId}")
    private static long workId;

    @Value("${snowflake.datacenterId}")
    private static long datacenterId;

    private static SnowFlake idGen=new SnowFlake(workId,datacenterId);

    @Override
    public int regist(@Param("info") userAccountInfo info){
        if(ifexist(info.username)) {
            log.warn("already exist user {}", info.username);
            return -1;
        }
        return loginmapper.insert(info);
    }
    
    @Override
    public userAccountInfo genByPwd(String password){//nickname后续自定义
        userAccountInfo info=new userAccountInfo();
        Date cur=new Date(System.currentTimeMillis());
        SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
        String date=fmt.format(cur);
        
        info.username=String.valueOf(idGen.nextId());
        info.createtime=date;
        info.salt=MD5SaltPwd.getSalt();
        info.password=MD5SaltPwd.getSaltedPwd(password,info.salt);

        return info;
    }

    @Override
    public boolean ifexist(String username)
    {
        List<userAccountInfo> users=loginmapper.getByUsername(username);
        if(users==null||users.size()==0) return false;
        return true;
    }

    @Override
    public boolean check(String username,String password)
    {
        List<userAccountInfo> users=loginmapper.getByUsername(username);
        if(users==null||users.size()==0) return false;
        if(users.size()>1) log.warn("duplicate users, num="+users.size());//警告重复用户
        userAccountInfo user=users.get(0);
        return MD5SaltPwd.verify(password, user.salt, user.password);
    }

    @Override
    public userAccountInfo getByUsername(String username){
        List<userAccountInfo> users=loginmapper.getByUsername(username);
        if(users==null||users.size()==0) 
        {
            log.warn("no user {}",username);
            return null;
        }
        if(users.size()>1) log.warn("duplicate users, num="+users.size());//警告重复用户
        return users.get(0);
    }
}
