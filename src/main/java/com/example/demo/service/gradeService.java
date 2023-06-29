package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.grade.entity.gradeItem;

@Transactional
public interface gradeService {
    List<gradeItem> getAll();
}
