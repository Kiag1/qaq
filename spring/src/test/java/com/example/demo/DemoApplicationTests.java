package com.example.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	private StudentMapper studentMapper;
	@Test
	void contextLoads() {
		Page<Student> page = new Page<>(1, 5);
		studentMapper.selectPage(page, null);
		System.out.println("size: " + page.getSize());
		System.out.println("total: " + page.getTotal());
		System.out.println("pages: " + page.getPages());

	}
}
