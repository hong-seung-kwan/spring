package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherTest {

	@Autowired
	Teacher teacher;
	
	@Test
	public void 연습6테스트() {
		teacher.teach();
	}
}
