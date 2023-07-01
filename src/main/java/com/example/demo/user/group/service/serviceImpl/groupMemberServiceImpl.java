package com.example.demo.user.group.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.group.dao.groupMemberMapper;
import com.example.demo.user.group.dao.entity.groupMember;
import com.example.demo.user.group.service.groupMemberService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Service
public class groupMemberServiceImpl implements groupMemberService{
    @Autowired
    groupMemberMapper memberMapper;
    
    @Override
    public void initGroup(String ownername,String groupid){
        groupMember member=new groupMember(groupid,ownername,"owner");
        memberMapper.insert(member);
    }

    @Override
    public List<groupMember> getMembers(String groupid){
        return memberMapper.getMemberList(groupid);
    }
    
    @Override
    public String getRole(String username,String groupid){
        groupMember member=memberMapper.getMember(username, groupid);
        if(member==null) return null;
        return member.grouprole;
    }

    @Override
    public int setRole(String username,String groupid,String role){
        groupMember member=memberMapper.getMember(username, groupid);
        if(member==null) return -1;
        log.info("user[{}] in group[{}], role switch from {} to {}",
                    username,groupid,member.grouprole,role);
        member.grouprole=role;
        return memberMapper.updateById(member);
    }

    @Override
    public int kick(String username,String groupid){
        groupMember member=memberMapper.getMember(username, groupid);
        if((member.username).equals("")||member==null) return -1;
        if(member.grouprole=="owner") return -1;
        return memberMapper.deleteMember(username, groupid);
    }

    @Override
    public int addMember(String username,String groupid){
        if(memberMapper.getMember(username, groupid)!=null) return -1;
        groupMember member=new groupMember(groupid,username,"member");
        return memberMapper.insert(member);
    }

    @Override
    public int delGroup(String groupid){
        List<groupMember> members=getMembers(groupid);
        if(members==null) return 0;
        // for(groupMember member:members){
        //     if(kick(member.username, groupid)==-1)
        //         return -1;
        // }
        return 0;
    }
}
