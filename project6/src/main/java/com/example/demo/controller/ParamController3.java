package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.UserDTO;

/* 
 * spring mvc는 웹사이트를 구현하는 코드를
 * model, view, controller로 분리하여 작성하는 방식.
 * controller는 사용자의 요청을 처리하는 컴포넌트
 * 
 * */



@Controller
public class ParamController3 {
	
	// 회원 정보를 등록하는 폼을 반환하는 메소드
	// 반환값: 폼양식 HTML 파일
	
	// 리턴타입: string 또는 void
	// string: html 파일의 경로 직접 입력
	
	@GetMapping("/register")
	public String registerForm() {
		return "/member/register";
	}
	
	// 회원 정보를 전달받는 메소드
	// 매개변수: 폼에서 전달한 회원정보를 파라미터로 수집
	// 반환값: 처리 결과
	@PostMapping("/register")
	@ResponseBody
	public String register(@ModelAttribute UserDTO dto) {
		
		System.out.println(dto);
		
		return "ok";
	}
	
}
