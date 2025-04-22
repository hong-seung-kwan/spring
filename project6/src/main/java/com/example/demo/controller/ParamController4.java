package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.BookDTO;

@Controller
public class ParamController4 {
	
	// 메세지 바디에 데이터를 담아서 전달
	
	// 반환값: "ok" 문자열
	
	// @RequestBody는 json 데이터를 class 객체로 변환하는 역할.
	// JacksonConverter가 json 데이터를 객체로 변환하여 파라미터에 담아줌
	@ResponseBody
	@GetMapping("/ex1")
	public String ex1(@RequestBody BookDTO dto ) {
		System.out.println("파라미터 수집: " + dto);
		return "ok";
	}
	
	// 메세지 바디에 객체 리스트 데이터 전달하기
	// @RequestBody는 JacksonConverter를 사용해서
	// json string을 class로 변환하고, 파라미터에 담아줌
	@ResponseBody
	@GetMapping("/ex2")
	public String ex2(@RequestBody List<BookDTO> list) {
		for(BookDTO dto : list) {
			System.out.println(dto);
		}
			
		return "ok";
	}
}
