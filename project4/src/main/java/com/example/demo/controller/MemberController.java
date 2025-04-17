package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// MVC 패턴으로 회원 관리 프로그램 만들기

// C: Controller
// Controller란? 사용자의 요청을 처리하는 컴포넌트
// 오직 요청만 처리. 화면X

@Controller
@RequestMapping("/v2") // url 경로 - 중간 경로
public class MemberController {
	
	// 회원등록폼을 반환하는 메소드
	@GetMapping("/form") // url 경로 - 마지막 경로
	// 이 함수의 전체 경로: /v2/form
	public String method1() {
		return "/v2/form"; // jsp 파일의 경로
	}
}
