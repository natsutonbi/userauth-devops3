package com.example.demo.grade.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.grade.dao.entity.gradeItem;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface gradeMapper extends BaseMapper<gradeItem>{
	@Select("SELECT * FROM `GradeDB`.`GRADE`")
    List<gradeItem> getAll();

	@Select("SELECT * FROM `GradeDB`.`GRADE` WHERE `SID` = #{SID} AND `CID` = #{CID}")
    List<gradeItem> findBy_SID_CID(@Param("SID") String SID,@Param("CID") String CID);

    @Insert("INSERT INTO `GradeDB`.`GRADE` (`SID`, `CID`, `Score`, `Note`) VALUES(#{grade.SID}, #{grade.CID}, #{grade.Socre}, #{grade.Note})")
    int insert(@Param("grade") gradeItem grade);
}