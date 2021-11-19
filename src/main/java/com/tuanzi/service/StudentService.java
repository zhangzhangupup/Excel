package com.tuanzi.service;

import com.tuanzi.dao.StudentMapper;
import com.tuanzi.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> UserExcelDownloads() {
        return studentMapper.UserExcelDownloads();
    }
}
