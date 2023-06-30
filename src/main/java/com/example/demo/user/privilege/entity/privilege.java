package com.example.demo.user.privilege.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.user.group.dao.entity.group;

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
@TableName("`User`.`privilege`")
public class privilege extends Model<group>{
    public String id;//索引
    public String name;//展示
    public String action;//具体命令(访问,禁止访问 | 上传,删除,隐藏 | 以及设置赋权;userService里面的操作)
    public String objtype;//url，user，group；实际上resource也可以由url指定
    public String objid;//user和group是具体id或者是all；url类似于拦截器模式



}
