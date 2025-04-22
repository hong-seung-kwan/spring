package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.StudentDTO;

@RequestMapping("/return")
@Controller
public class Quiz3Controller {
	
	@GetMapping("/q1")
	public String q1(Model model) {
		model.addAttribute("data","첫번째퀴즈입니다");
		return "/sample/q1";
	}
	@GetMapping("/q2")
	public String q2(Model model) {
		model.addAttribute("data","두번째퀴즈입니다");
		return "/sample/q2";
	}
	@ResponseBody
	@GetMapping("/q3")
	public StudentDTO q3() {
		StudentDTO dto = new StudentDTO(1, "둘리", 3);	
		return dto;			
	}
	@ResponseBody
	@GetMapping("/q4")
	public CarDTO q4() {
		CarDTO dto = CarDTO.builder().company("현대").model("코나").color("블랙").build();
		return dto;
	}
	@ResponseBody
	@GetMapping("/q5")
	public List<StudentDTO> q5(){
		
		List<StudentDTO> list = new ArrayList<>();
		list.add(new StudentDTO(1, "둘리", 3));
		list.add(new StudentDTO(2, "또치", 1));
		list.add(new StudentDTO(3, "도우너", 2));
		return list;
	}
	
	@GetMapping("/q6")
	public ResponseEntity q6() {	
		return new ResponseEntity<String>("response fail..", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/q7")
	public ResponseEntity<CarDTO> q7() {
		CarDTO dto = new CarDTO("현대", "코나", "블랙");
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
}
