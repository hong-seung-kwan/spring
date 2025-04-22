package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

// 응답을 보내는 방법

@Controller
public class ReturnController1 {
	
	// ModelAndView: 화면과 데이터를 담는 객체
	
	@GetMapping("/return1")
	public ModelAndView ex1() {
		ModelAndView modelAndView = new ModelAndView("sample/sample1");
		modelAndView.addObject("data","banana");
		
		return modelAndView;
	}
	
	// Model: 화면에 데이터를 전달하는 객체
	
	/* 
	 * spring 초기에는 ModelAndView를 사용했음.
	 * 스프링 버전이 올라가면서 model이 도입됨
	 * model은 데이터만 처리하기 때문에 코드가 더 깔끔함
	 * */
	
	@GetMapping("/return2")
	public String ex2(Model model) {
		// 모델에 데이터 담기
		model.addAttribute("data","banana");
		// html 파일 반환
		return "sample/sample2";		
	}
	
	/*
	 * spring은 기본적으로 html 문서를 반환하도록 설계되어있음
	 * 리턴타입이 string이면 html 파일 경로를 직접 작성
	 * 리턴타입이 void이면 url 경로가 파일의 경로가 됨
	 * */

	@GetMapping("/sample/sample3")
	public void ex3(Model model) {
		model.addAttribute("data","banana");
	}
	
}
