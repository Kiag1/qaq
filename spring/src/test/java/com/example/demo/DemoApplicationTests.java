package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	private StudentMapper studentMapper;
	@Test
	void contextLoads() {
		Student student = new Student("喜羊羊","搬砖","2007310324",25,"2022-2-2");
		studentMapper.insert(student);
	}
}
