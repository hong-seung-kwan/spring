package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.StudentDTO;

@Controller
@RequestMapping("/params")
public class QuizsController {
	
	// url을 통해 파라미터 전달
	
	// URL: /param/q1?str=hello
	@GetMapping("/q1")
	@ResponseBody
	public String quiz1(@RequestParam(name = "str") String str) {
		System.out.println("파라미터 수집: " + str);
		return "ok";
	}
	
	// 메세지 바디를 통해 학생정보를 전달하세요
	
	// @RequestBody는 컨버터를 사용하여
	// json string을 class로 변환해서 파라미터에 넣어줌
	@PostMapping("/q4")
	@ResponseBody
	public String quiz4(@RequestBody StudentDTO dto ) {
		System.out.println("객체 수집: " + dto);		
		return "ok";
	}
	
}
