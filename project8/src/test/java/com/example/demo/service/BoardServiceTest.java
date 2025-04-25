package com.example.demo.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;

// BoardService의 기능(데이터 조회,추가,수정,삭제)를 확인하는 클래스

@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	BoardService service;
	
	@Test
	void 게시물등록() {
	
		// board table의 writer는 member 테이블을 참조하기 때문에
		// member table에 없는 아이디는 사용할 수 없다
		
		// board dto 생성
		// 작성자는 user1 또는 user2
		BoardDTO dto = BoardDTO.builder()
									.title("하이")
									.content("하이")
									.writer("user1")
									.build();
		// 테이블에 새로운 게시물 추가
		service.register(dto);
	}
	
	@Test
	void 게시물조회() {
		
		// 게시물 번호는 테이블에 있는 번호로 선택
		BoardDTO dto = service.read(2);
		
		System.out.println(dto);
	}
	
}
