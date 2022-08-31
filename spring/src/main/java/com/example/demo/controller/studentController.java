package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.StudentPage;
import com.example.demo.common.Result;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = {"*", "null"})//跨域
@RestController
@SuppressWarnings("all")
public class studentController {
    @Autowired
    StudentMapper studentMapper;
    Gson gson = new Gson();

    @GetMapping("/student")
    public String getStudent() {
        List<Student> students = studentMapper.selectList(null);
        return gson.toJson(students);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentMapper.insert(student);
    }

    @PostMapping("/delete")
    public void deleteStudent(@RequestBody Student student) {
        studentMapper.deleteById(student);
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody Student student) {
        studentMapper.updateById(student);
    }
}
