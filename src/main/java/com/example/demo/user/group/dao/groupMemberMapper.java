package com.example.demo.user.group.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.group.dao.entity.groupMember;

@Mapper
public interface groupMemberMapper extends BaseMapper<groupMember>{
    @Select("SELECT * FROM `User`.`group_member` WHERE groupid=#{groupid}")
    List<groupMember> getMemberList(@Param("groupid") String groupid);

    @Select("SELECT * FROM `User`.`group_member` WHERE groupid=#{groupid} and username=#{username}")
    groupMember getMember(@Param("username")String username,@Param("groupid")String groupid);

    @Delete("DELTE FROM `User`.`group_member` WHERE username=#{username} and groupid=#{groupid}")
    int deleteMember(@Param("username") String username,@Param("groupid")String groupid);
}
