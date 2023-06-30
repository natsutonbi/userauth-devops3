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
@TableName("`User`.`permissions`")
public class permission extends Model<permission>{
    public String path;
    public String methods;//all,get,post,put,n-get,n-all
    
    @TableField("permission_name")
    public String name;
    @TableField("permission_description")
    public String descript;
    
    public Timestamp created_time;
    public Timestamp outdate_time;
}
