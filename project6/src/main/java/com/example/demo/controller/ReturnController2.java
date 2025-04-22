package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.BookDTO;

// 순수한 문자열 데이터 보내기

@RequestMapping("/return2")
@Controller
public class ReturnController2 {

	/* 
	 * spring은 기본적으로 html파일을 반환하도록 설계되어 있음
	 * 함수에 @ResponseBody을 붙이면 순수한 데이터가 반환됨
	 * 메세지 바디에 "hi~"라는 문자열이 들어감
	 * */
	
	
	
	@ResponseBody
	@GetMapping("/ex1")
	public String ex1() {
		return "hi~";
	}
	
	
	@ResponseBody
	@GetMapping("/ex2")
	public int ex2() {
		return 100;
	}
	
	// 객체 데이터 전달
	
	/* @ResponseBody을 사용하면 컨버터가
	 * 인스턴스를 json 문자열로 변환하여 사용자에게 전달함
	 * */
	
	@ResponseBody
	@GetMapping("/ex3")
	public BookDTO ex3() {
		BookDTO dto = BookDTO.builder()
								.title("자바 프로그래밍 입문")
								.publisher("한빛컴퍼니")
								.price(20000)
								.build();
		return dto;
	}
	
	// 사용자에게 리스트 보내기
	
	/*
	 * @ResponseBody는 컨버터를 사용하여 객체를 변환하여 전송함
	 * string이나 int 같은 단순한 값은 string 컨버터를 사용하고
	 * 객체는 jackson 컨버터를 사용함
	 * */
	@ResponseBody
	@GetMapping("/ex4")
	public List<BookDTO> ex4(){
		
		List<BookDTO> list = new ArrayList<>();
		list.add(new BookDTO("자바프로그래밍입문", "한빛컴퍼니", 20000));
		list.add(new BookDTO("스프링부트웹프로젝트", "구멍가게코딩단", 15000));
		list.add(new BookDTO("모두의리눅스", "길벗출판사", 30000));
		
		return list;
		
	}
	
	/* ResponseEntity: 응답 코드와 데이터를 함께 작성할때 사용
	 * 응답코드는 header에 데이터는 body에 들어감
	 * 데이터는 생략 가능
	 * */
	
	@GetMapping("/ex5")
	public ResponseEntity ex5() {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
	}
	
	@GetMapping("/ex6")
	public ResponseEntity<String> ex6() {
		// 201 ok 코드
		// success.. 문자열 데이터
		return new ResponseEntity<String>("success..", HttpStatus.CREATED);
	}
	
	// 헤더에는 201 코드를 담고
	// 바디에는 도서정보 담기
	
	@GetMapping("/ex7")
	public ResponseEntity<BookDTO> ex7(){
		BookDTO dto = new BookDTO("자바~", "한빛컴퍼니", 20000);
		
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
}
