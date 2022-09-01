package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    @GetMapping("/student") //获取数据
    public String getStudent() {
        List<Student> students = studentMapper.selectList(null);
        return gson.toJson(students);
    }

    @PostMapping("/add")
    public Result<Student> addStudent(@RequestBody Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", student.getId()).last("limit 1"); //判断是否数据库中是否存在该数据
        Long i = studentMapper.selectCount(queryWrapper);
        if (i > 0) {
            return new Result<>(false, null, student);
        } else {
            studentMapper.insert(student);
            String msg = student.getId();
            if (msg != null) {
                return new Result<>(true, msg, student);
            }
        }
        return new Result<>(false, null, student);
    }

    @PostMapping("/delete")
    public void deleteStudent(@RequestBody Student student) {
        studentMapper.deleteById(student);
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody Student student) {
        studentMapper.updateById(student);
    }

    @GetMapping("/findpage") //分页返回表格
    public StudentPage<Student> findStudent(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                            @RequestParam(defaultValue = "") String search) {
        StudentPage<Student> SPage =new StudentPage<>();
        LambdaQueryWrapper<Student> lambdaQueryWrapper = Wrappers.<Student>lambdaQuery();
        Page<Student> page = new Page<>(pageNum, pageSize);
        if(StrUtil.isNotBlank(search)){ //名字为空
            lambdaQueryWrapper.like(Student::getName, search);
        }
        Page<Student> pageResult = studentMapper.selectPage(page, lambdaQueryWrapper);
        SPage.setContentList(pageResult.getRecords());
        SPage.setCurrentPage(pageResult.getCurrent());
        SPage.setTotalPage(pageResult.getPages());
        SPage.setTotalNum(pageResult.getTotal());
        return SPage;
    }
}
