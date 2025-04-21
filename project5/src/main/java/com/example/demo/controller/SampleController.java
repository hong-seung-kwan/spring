package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.SampleDTO;

// MVC 패턴으로 사이트 만들기

// C: Controller (사용자 요청을 처리하는 컴포넌트)

// @Controller 안에는 @Component가 포함되어 있음
// @Component는 해당 클래스를 컨테이너의 빈으로 등록하는 어노테이션
// SampleContoroller는 프로젝트가 실행될 때, 자동으로 빈으로 등록됨
// 그리고 외부에서 요청이 들어오면 해당 빈이 실행됨
@Controller
// sample은 중간경로
// 클래스 안에 있는 모든 함수에 적용됨
@RequestMapping("/sample") // 중간경로

public class SampleController {
	@GetMapping("/ex1") // 전체경로: /sample/ex1
	public void ex1() {
		// 리턴값: view의 경로
		// string -> 파일의 경로 직접 작성
		// void -> url 경로가 파일의 경로가 됨(sample 폴더 아래 ex1.html)
	}
	
	// Model: 컨트롤러에서 화면으로 데이터를 전달하는 객체
	@GetMapping("/ex2") // 전체경로: /sample/ex2
	public void ex2( Model model) {
		// 리턴타입이 void => url 경로가 파일의 경로가 됨 (sample 폴터 아래 ex2.html)
		
		// 모델에 데이터 담기 (key, value)
		model.addAttribute("msg","반가워요");	
	}
	
	// 전체 경로: /sample/ex3
	// // Model: 컨트롤러에서 뷰로 데이터를 전달하는 객체
	@GetMapping("/ex3") // 전체 경로: /sample/ex3
	public void ex3(Model model) { // 파일의 경로: sample 폴더 아래 ex3.html
		
		model.addAttribute("msg1","안녕하세요");
		model.addAttribute("msg2","반가워요");
	}
	
	
	// Model: controller 에서 view로 데이터를 전달하는 객체
	@GetMapping("/ex4")
	public void ex4(Model model) {
		
		// 모델에 데이터 담기
		
		LocalDate date = LocalDate.of(2025, 4, 18);
		SampleDTO dto = SampleDTO.builder()
											.no(1)
											.text("hello")
											.regDate(date)
											.build();
		// 화면에 객체 데이터를 전달
		model.addAttribute("dto",dto);
	}

	// {주소1,주소2}
	// /sample/ex5 또는 /sample/ex6 /sample/ex7 요청 들어오면 ex5 메소드가 처리
	@GetMapping({"/ex5", "/ex6", "/ex7", "/ex8"})
	// 왜 동일한 함수를 사용할까? 화면에 전달하는 데이터가 같기 때문에
	public void ex5(Model model) { 
		
		// SampleDTO 타입 리스트 생성
		List<SampleDTO> list = new ArrayList<>();
		
		// LocalDate.now() => 현재시간 반환
		// LocalDate.of(0,0,0) => 우리가 지정한 시간
		// 데이터추가
		list.add(new SampleDTO(1,"aa",LocalDate.now()));
		list.add(new SampleDTO(2,"bb",LocalDate.now()));
		list.add(new SampleDTO(3,"cc",LocalDate.now()));
		
		// model에 리스트 데이터 담기
		// 화면으로 리스트 전달
		model.addAttribute("list",list);
	}
	
	@GetMapping("/ex9")
	public void ex9(Model model) {
		List<SampleDTO> list = new ArrayList<>();
		// DTO 타입 리스트 생성
		for(int i =1; i<=20;i++) {
			SampleDTO dto = new SampleDTO(i, i+"번", LocalDate.now());
			list.add(dto);
		}
		// 화면에 리스트 전달
		model.addAttribute("list",list);
	}
	
	// 전체경로: /sample/ex10
	// 리턴타입이 void => url 경로가 파일의 경로
	// 리턴타입이 string => 파일의 경로를 직접 작성
	@GetMapping("/ex10")
	public void ex10(Model model) {
		SampleDTO dto = new SampleDTO(1, "aaa", LocalDate.now());
		
		// 화면에 객체 전달
		model.addAttribute("dto",dto);
		// 화면에 문자열 데이터를 전달
		model.addAttribute("msg","seccess");
	}
	
	// 전체 URL: /sample/ex11
	// 반환타입: void => url 경로가 파일의 경로가 됨
	// 파일의 경로: sample 폴더 아래 ex11.html
	// 매개변수: 파라미터 또는 model
	// model: 컨트롤러에서 화면으로 데이터를 전달하는 객체
	@GetMapping("/ex11")
	public void ex11(Model model) {
		
		// 화면에 현재시간을 전달
		model.addAttribute("date", LocalDateTime.now());
	}
}
