package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.StudentDTO;

@RequestMapping("/param")
@Controller
public class quizController {
	
	@ResponseBody
	@GetMapping("/q1")
	public String q1(@RequestParam(name = "str") String str) {
		System.out.println("String타입 파라미터 수집: " + str);
		return "ok";
	}
	
	@ResponseBody
	@GetMapping("/q2")
	public String q2(@RequestParam(name = "f") float f, @RequestParam(name = "b") boolean b) {
		System.out.println("float타입 파라미어 수집:"+ f +", boolean타입 파라미터 수집: " + b);
		return "ok";
	}
	
	@ResponseBody
	@GetMapping("/q3")
	public String q3(@RequestParam(name="arr") char[] arr) {
		System.out.println("char형 배열 수집: "+ Arrays.toString(arr));
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println("배열의 개수: "+ arr.length);
		return "ok";
	}
	
	@ResponseBody
	@PostMapping("/q4")
	public String q4(@RequestBody StudentDTO dto) {
		System.out.println("객체수집: "+ dto);
		return "ok";
	}
	
	@ResponseBody
	@PostMapping("/q5")
	public String q5(@RequestBody List<StudentDTO> list) {
		System.out.println("객체타입 리스트 수집: " + list);
		for(StudentDTO dto : list) {
			System.out.println(dto);			
		}
		System.out.println("리스트의 개수: "+ list.size());
		return "ok";
	}
	
	@ResponseBody
	@PostMapping("/q6")
	public String q6(@RequestBody List<CarDTO> list) {
		System.out.println("객체타입 리스트 수집:" + list);
		for(CarDTO dto :list) {
			System.out.println(dto);
		}
		System.out.println("리스트 마지막 요소: " + list.get(list.size()-1));
		return "ok";
	}
	
}
