package com.example.demo.security.mapper.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@TableName("permission")
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends Model<Permission> {
    String username;
    String permission;
}
