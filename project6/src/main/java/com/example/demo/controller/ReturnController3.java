package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller, @ResponseBody 포함
// 클래스 내부의 모든 함수에 @ResponseBody가 적용됨
@RestController
public class ReturnController3 {

	/*
	 * spring은 기본적으로 html 파일을 반환하도록 설계되어 있음 아래 함수는 String 타입이므로 "hi~.html" 파일을 찾아서
	 * 반환하지만 실제 경로에는 파일이 없기때문에 에러가 발생함
	 */

	@GetMapping("/hi")
	public String ex1() {
		return "hi~";
	}

	@GetMapping("/hello")
	public String ex2() {
		return "hello~";
	}
}
