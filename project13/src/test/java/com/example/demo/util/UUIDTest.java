package com.example.demo.util;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class UUIDTest {
	@Test
	public void 테스트() {
		String str = UUID.randomUUID().toString();
		System.out.println(str.substring(0,10));
	}
	
	@Test
	void 테스트2() {
		String originFilename = "사과.png";
		int index = originFilename.lastIndexOf(".");
		String extention = originFilename.substring(index+1);
		
		System.out.println(index);
		System.out.println(extention);
	}
}
