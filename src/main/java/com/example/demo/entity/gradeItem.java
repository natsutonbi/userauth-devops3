package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data//生成getter，setter，toString
@AllArgsConstructor
@NoArgsConstructor//生成无参构造
@Accessors(chain = true)//set返回this,可以class.setA(a).setB(b).setC(c);
@TableName("GRADE")
public class gradeItem
{
    public int SID,CID;
    public float Score;
    public String Note;
}
