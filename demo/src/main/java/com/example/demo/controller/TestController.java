package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/hello")
	public String hello() {
		return "안녕하세요";
	}
}


// 스프링 부트 프로젝트는 내장 tomcat을 포함하고 있다
// spring 프로젝트를 실행할 때는 tomcat이라는 프로그램을 사용해야한다
// 프로젝트를 실행하는 방법:
// boot dashboard > local > 프로젝트의 tomcat 선택 > run
// 각 tomcat은 포트를 할당받는다

// spring 프로젝트의 목적: web 사이트 구현
// web 사이트는? 24시간 돌아감
// 따라서 순수한 자바 프로젝트와 달리 직접 정지하기전까지 실행됨
