package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ChatRequest;
import com.example.demo.gpt.service.GptService;

// 사용자의 요청을 받아 운세정보를 알려주는 컴포넌트

@Controller
public class GptContoller {
	
	@Autowired
	GptService service;
	
	/*
	 * 스프링 부트 프로젝트는 기본적으로 html 문서를 반환하도록 설계되어 있음
	 * 순수한 데이터를 반환할때는 @responsebody와 같은 어노테이션 사용해야함
	 * */
	
	// 운세정보를 반환하는 메소드
	// 반환값: 순수한 운세 정보
	// 채팅 데이터를 파라미터로 수집
	@ResponseBody // html 문서 대신 문자열 반환
	@PostMapping("/fortuneTell")
	// json string -> class 변환
	public String fortuneTell(@RequestBody ChatRequest request) {
		
		System.out.println("파라미터 수집: " + request);
		
		String response = service.callGptApi(request);
		return response;
	}
	
	// 화면을 반환하는 메소드
	// 반환값: 화면
	@GetMapping("/fortune")
	public String fortune() {
		return "index"; // HTML 파일 경로
	}
	
}






/* 화면의 종류: 
 * 1. 데이터를 함께 표시하는 화면
 * 2. 데이터가 없는 순수한 화면
 * */
 