package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.userAccountInfo;

@Mapper
public interface loginUserMapper extends BaseMapper<userAccountInfo> {
    @Select("SELECT * FROM `User`.`user_login` WHERE username=#{username}")
    public List<userAccountInfo> getByUsername(@Param("username") String username);

    @Insert("INSERT INTO `User`.`user_login` (username,createtime,salt,password,nickname) VALUES "+
    "(#{username},#{createtime},#{salt},#{password},#{nickname})")
    public int insert(userAccountInfo info);

    @Update("UPDATE `User`.`user_login` SET password=#{password} nickname=#{nickname} WHERE username=#{username}")
    public int update(userAccountInfo info);
}
