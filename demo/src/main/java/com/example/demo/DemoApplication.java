package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	// 메인함수: 프로그램의 시작점
	// spring 프로젝트는 메인클래스를 하나만 가질 수 있다
	// spring 프로젝트는 여러 클래스를 연결하여 하나의 사이트를 만든다
	// 순수 java 프로젝트는 메인클래스를 여러개 가져도 상관 없다
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
