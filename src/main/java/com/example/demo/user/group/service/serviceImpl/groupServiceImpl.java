package com.example.demo.user.group.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.user.group.dao.groupMapper;
import com.example.demo.user.group.entity.group;
import com.example.demo.user.group.service.groupMemberService;
import com.example.demo.user.group.service.groupService;
import com.example.demo.user.service.userService;
import com.example.demo.user.utils.SnowFlake;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Configuration
public class groupServiceImpl implements groupService {
    @Autowired
    private groupMapper gMapper;

    @Autowired
    private groupMemberService memberService;

    @Value("${snowflake.group.workId}")
    private static long workId;

    @Value("${snowflake.datacenterId}")
    private static long datacenterId;

    private static SnowFlake gidgen=new SnowFlake(workId, datacenterId);

    @Override
    public group newGroup(String username)
    {
        String gid=String.valueOf(gidgen.nextId());
        group g=new group(gid,username,"unamed_group");
        gMapper.insert(g);
        log.info("新建用户组 by user[{}]，groupid={}",username,gid);
        memberService.initGroup(username, gid);
        return g;
    }

    @Override
    public group getByID(String groupid)
    {
        List<group> groups=gMapper.getByGroupID(groupid);
        if(groups==null||groups.size()<1) return null;
        if(groups.size()>1) log.warn("more than one group, groupid={}", groupid);
        return groups.get(0);
    }

    @Override
    public List<group> getAll(){
        return gMapper.getAll();
    }
    @Override
    public int rename(String groupid,String newname){
        group g=getByID(groupid);
        if(g==null) return -1;
        g.groupname=newname;
        return gMapper.updateById(g);
    }
    
    @Autowired
    userService userv;

    @Override
    public int transOwner(String groupid,String username){
        if(!userv.ifexist(username)) return -1;
        group g=getByID(groupid);
        if(g==null) return -1;
        g.owner=username;
        return gMapper.updateById(g);
    }

    @Override
    public int delete(String groupid)
    {
        memberService.delGroup(groupid);
        return gMapper.deleteById(groupid);
    }
}
