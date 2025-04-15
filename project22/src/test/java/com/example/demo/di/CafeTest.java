package com.example.demo.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CafeTest {
	
	@Autowired
	Cafe cafe;
	@Autowired
	Manager manager;
	
	@Test
	public void 연습4테스트() {
		System.out.println("cafe: " + cafe);
		System.out.println("Manager: " + manager);
	}
}
