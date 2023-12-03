package com.example.demo.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.security.mapper.entity.Account;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Mapper
public interface UserMapper extends BaseMapper<Account> {
    @Select("SELECT role FROM role WHERE username = #{username}")
    Set<SimpleGrantedAuthority> getRoles(@Param("username") String username);

    @Select("SELECT permission FROM permission WHERE username = #{username}")
    Set<SimpleGrantedAuthority> getPermission(@Param("username") String username);

    @Insert("INSERT INTO role (username,role) VALUES (#{username},#{role})")
    int addRole(@Param("username") String username,@Param("role") String role);

    @Insert("INSERT INTO role (username,role) VALUES (#{username},#{permission})")
    int addPermission(@Param("username") String username,@Param("permission") String permission);
}
