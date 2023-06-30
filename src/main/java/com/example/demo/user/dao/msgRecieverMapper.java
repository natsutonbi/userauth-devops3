package com.example.demo.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.dao.entity.msgReciever;

@Mapper
public interface msgRecieverMapper extends BaseMapper<msgReciever>{
    @Select("SELECT * FROM `User`.`message_recieve` WHERE username=#{username}")
    public List<msgReciever> getByUsername(@Param("username")String username);

    @Insert("INSERT INTO `User`.`message_recieve` (username,email,tel) VALUES "+
    "(#{username},#{email},#{tel})")
    public int insert(msgReciever reciever);

    @Update("UPDATE `User`.`message_recieve` SET email=#{email},tel=#{tel} WHERE username=#{username}")
    public int update(msgReciever reciever);
}
