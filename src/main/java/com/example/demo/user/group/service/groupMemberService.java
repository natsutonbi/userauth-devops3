package com.example.demo.user.group.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.user.group.dao.entity.groupMember;

@Service
public interface groupMemberService {
    void initGroup(String ownername,String groupid);

    List<groupMember> getMembers(String groupid);

    String getRole(String username,String groupid);

    int setRole(String username,String groupid,String role);

    int kick(String username,String groupid);

    int addMember(String username,String groupid);

    int delGroup(String groupid);
}
