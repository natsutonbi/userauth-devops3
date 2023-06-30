package com.example.demo.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.dao.msgRecieverMapper;
import com.example.demo.user.dao.entity.msgReciever;
import com.example.demo.user.service.userManageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class userManageServiceImpl implements userManageService{
    @Autowired
    msgRecieverMapper manageMapper;

    @Override
    public msgReciever getRecieverByUsername(String username)
    {
        List<msgReciever> recievers=manageMapper.getByUsername(username);
        if(recievers==null||recievers.size()==0) 
        {
            log.warn("no user {}",username);
            return null;
        }
        if(recievers.size()>1) log.warn("duplicate users in message_recieve table, num="+recievers.size());//警告重复用户
        return recievers.get(0);
    }

    @Override
    public int updateReciever(msgReciever reciever){
        return manageMapper.update(reciever);
    }

    @Override
    public int initReciever(msgReciever reciever){
        return manageMapper.insert(reciever);
    }
}
