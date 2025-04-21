package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 컨트롤러: 사용자의 요청을 처리하는 컴포넌트

// 중복되는 URL을 중간경로로 설정
// 클래스 레벨에 경로를 설정하면 모든 함수에 적용됨
@RequestMapping("/board")
@Controller
public class MappingController1 {
	
	// 함수: 사용자의 요청을 처리
	
	// URL 주소를 작성
	// value: 주소 method: 작업의 종류
	// 리턴값: string
	// 기본적으로 화면(html)이 반환됨
	// 화면 대신 문자열 반환하기
	@ResponseBody
//	@RequestMapping(value = "/board",method =RequestMethod.GET )
	// 축약 어노테이션으로 교체
	@GetMapping
	public String list() {
		System.out.println("게시물 조회..");
		return "ok";
	}
	// 메소드가 다르면 url주소가 같아도 중복 아님
	// 문자열을 반환
	@ResponseBody
//	@RequestMapping(value = "/board", method = RequestMethod.POST)
	@PostMapping
	public String save() {
		System.out.println("게시물 등록..");
		return "ok";
	}
	// URL 주소: PUT + localhost:8080/board
	@ResponseBody
//	@RequestMapping(value = "/board", method = RequestMethod.PUT)
	@PutMapping
	public String modify() {
		System.out.println("게시물 수정..");
		return "ok";
	}
	// URL 주소: DELETE + localhost:8080/board
	@ResponseBody
//	@RequestMapping(value = "/board", method = RequestMethod.DELETE)
	@DeleteMapping
	public String remove() {
		System.out.println("게시물 삭제..");
		return "ok";
	}
}
