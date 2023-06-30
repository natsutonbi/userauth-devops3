package com.example.demo.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("`User`.`user_login`")
public class userAccountInfo extends Model<userAccountInfo> {
    //不变
    public String username;//实际上就是uid
    public String createtime;
    public String salt;

    public String password;
    public String nickname;//用户自定义
}
