package com.example.demo.user.group.service;

import org.springframework.stereotype.Service;

import com.example.demo.user.group.entity.group;

@Service
public interface groupService {
    //增
    group newGroup(String username);//指定owner，由创建人担任owner，同时插入数据库
    //查
    group getByID(String groupid);
    //改
    int rename(String groupid,String newname);
    int transOwner(String groupid,String username);
    //删
    int delete(String groupid);
}
