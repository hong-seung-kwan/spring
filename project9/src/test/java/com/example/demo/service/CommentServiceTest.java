package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.CommentDTO;

// CommentService의 기능을 테스트하는 클래스

@SpringBootTest
public class CommentServiceTest {
	
	@Autowired
	CommentService service;
	
	@Test
	void 서비스확인() {
		System.out.println("service: " + service);
	}
	
	@Test
	void 댓글등록() {
		
		CommentDTO dto = CommentDTO.builder()
										.boardNo(2)
										.content("댓글")
										.writer("user1")
										.build();
		
		service.register(dto);
	}
	
	@Test
	void 게시물별_댓글목록조회() {
		List<CommentDTO> list = service.getListByBoardNo(2);
		for(CommentDTO dto :list) {
			System.out.println(dto);
		}
	}
	@Test
	void 댓글삭제() {
		service.remove(9);
	}
}
