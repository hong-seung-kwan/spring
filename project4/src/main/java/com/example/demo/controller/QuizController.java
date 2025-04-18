package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz") // url경로 - 중간경로
public class QuizController {
	@GetMapping("/q1") // 전체경로 - /quiz/q1
	public String method() {
		return "/quiz/q1"; // jsp 파일 경로
	}
	
	@GetMapping("/q2") // 전체경로 - /quiz/q2
	public String method2() {
		return "/quiz/q2"; // jsp 파일 경로
	}
	

}
