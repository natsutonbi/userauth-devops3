package com.example.demo.user.privilege.dao.entity;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("`User`.`user_role`")
public class userRole extends Model<userRole>{
    public String username;
    @TableField("role_name")
    public String role;
    
    public Timestamp outdate_time;
    public String create_user;
    public Timestamp created_time;
    public String update_user;
    public Timestamp updated_time;
}
