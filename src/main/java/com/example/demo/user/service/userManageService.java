package com.example.demo.user.service;

import org.springframework.stereotype.Service;

import com.example.demo.user.entity.msgReciever;

@Service
public interface userManageService {
    msgReciever getRecieverByUsername(String username);
    
    int updateReciever(msgReciever reciever);

    int initReciever(msgReciever reciever);
}
