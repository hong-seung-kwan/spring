package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

@RestController
public class MemberController {

	@Autowired
    MemberService service;

	// 파라미터: 회원 데이터 (json 또는 xml 형식의 데이터)
	// 반환값: 처리 결과(json 데이터 메세지 바디에 포함)
	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody MemberDTO dto) {
		boolean result = service.register(dto);
		return new ResponseEntity<>(result, HttpStatus.CREATED); //201성공코드와 처리결과 반환
	}

	//localhost:8080/member/list
	@GetMapping("/member/list")
	public ResponseEntity<List<MemberDTO>> getList() {
		List<MemberDTO> list = service.getList();
		return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 회원목록 반환
	}

}
