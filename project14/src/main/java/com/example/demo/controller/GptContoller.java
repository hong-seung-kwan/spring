package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody // html 문서 대신 문자열 반환
	// 결과에 따라 string 컨버터 또는 json 컨버터가 동작
	@GetMapping("/fortuneTell")
	public String fortuneTell() {
		
		String response = service.callGptApi();
		return response;
	}
	
}
