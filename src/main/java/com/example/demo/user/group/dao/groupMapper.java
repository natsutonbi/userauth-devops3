package com.example.demo.user.group.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.group.dao.entity.group;

@Mapper
public interface groupMapper extends BaseMapper<group> {
    @Select("SELECT * FROM `User`.`group_info` WHERE groupid=#{groupid}")
    List<group> getByGroupID(@Param("groupid")String groupID);

    @Select("SELECT * FROM `User`.`group_info`")
    List<group> getAll();

    @Delete("DELETE FROM `User`.`group_info` WHERE groupid=#{groupid}")
    int deleteByGroupID(@Param("groupid")String groupID);
    //@Insert("INSERT INTO `User`.`group_info` (username,createtime,salt,password,nickname) VALUES "+
    //"(#{username},#{createtime},#{salt},#{password},#{nickname})")
}
