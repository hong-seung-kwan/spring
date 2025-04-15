package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeTest {

	@Autowired
	Employee employee;
	
	@Test
	public void 연습3테스트() {
		employee.work();
	}
}
