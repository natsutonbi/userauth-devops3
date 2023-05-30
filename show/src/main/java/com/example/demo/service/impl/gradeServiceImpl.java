package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.gradeMapper;
import com.example.demo.entity.gradeItem;
import com.example.demo.service.gradeService;

@Service
public class gradeServiceImpl implements gradeService {
    @Autowired
    private gradeMapper gradeM;

    @Override
    public List<gradeItem> getAll()
    {
        return gradeM.getAll();
    }
}
