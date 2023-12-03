package com.example.demo.grade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.grade.dao.entity.gradeItem;
import com.example.demo.grade.service.gradeService;

import org.springframework.ui.ModelMap;



@Controller
public class dbControllerMP {
    @Autowired
    private gradeService gService;

    @GetMapping("/v2/grade")
	public String grade(ModelMap map){
        List<gradeItem> res=gService.getAll();
        map.addAttribute("itemList", res);
        return "grade";
    }
}
