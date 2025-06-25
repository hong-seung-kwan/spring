package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping("/")
	// 반환값: spring boot는 기본적으로 html 문서 반환
	// void : url 경로 = 파일 경로
	// string: 파일 경로 직접 작성
	public String method() {
		return "ex1"; //파일 경로: /template/ex.html
	}
	
}
