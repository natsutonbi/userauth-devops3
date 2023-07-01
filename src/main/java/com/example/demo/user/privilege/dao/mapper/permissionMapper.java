package com.example.demo.user.privilege.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.user.privilege.dao.entity.permissionInfo;

@Mapper
public interface permissionMapper extends BaseMapper<permissionInfo> {
    @Select("SELECT * FROM `User`.`permissions` WHERE path=#{path},methods=#{methods}")
    public permissionInfo getInfo(@Param("path")String path,@Param("methods")String methods);

}
