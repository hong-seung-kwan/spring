package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;

// Controller: 사용자 요청을 처리하는 컴포넌트

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	/* 스프링은 기본적으로 html 파일을 반환하지만,
	 * @ResponseBody를 사용하면 순수한 데이터가 반환됨
	 * */
	
	// 게시물별 댓글 조회
	// 파라미터로 게시물 번호를 수집
	// 댓글 리스트를 반환
	// URL 예시: /comment/list?boardNo=1
	
	// 컨버터를 사용하여 class를 json string으로 변환
	@ResponseBody
	@GetMapping("/list")
	public List<CommentDTO> list(@RequestParam(name="boardNo") int boardNo){
		
		// 게시물 번호로 댓글 조회
		List<CommentDTO> list = service.getListByBoardNo(boardNo);
		
		return list;
	}
	
	// 댓글 등록
	// 파라미터로 새로운 댓글 데이터 수집
	// 매개변수로 인증객체 받기
	// 등록 메소드가 호출될때, 시큐리티가 인증객체를 넣어줌
	@ResponseBody
	@PostMapping("/register")
	public Boolean register(Principal principal,CommentDTO dto) {
		
		// 인증객체에서 아이디 꺼내기
		String id = principal.getName();
		// 꺼낸 아이디 dto에 넣기
		dto.setWriter(id);
		service.register(dto);
		return true;
	}
	
	// 댓글삭제
	// 댓글 번호 파라미터로 수집
	// URL: //comment/remove?commentNo=1
	@ResponseBody
	@DeleteMapping("/remove")
	public Boolean remove(@RequestParam(name = "commentNo") int commentNo) {
		
		boolean result = service.remove(commentNo);
		
		return result;
	}
	
}




