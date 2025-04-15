package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;

//BoardRepository의 기능을 테스트하는 클래스
@SpringBootTest
public class BoardRepositoryTest {
	// 테스트대상
	@Autowired
	BoardRepository repository;

	@Test
	public void 리파지토리확인() {
		System.out.println(repository);
	}

	@Test
	public void 게시물등록() {

		// 엔티티 생성
//		Board board = new Board(0, "1번", "내용입니다", null, null);
//		repository.save(board);

		// builder는 필요한 데이터만 입력할 수 있음
		Board board = Board.builder().title("3번").content("내용입니다").build();
		repository.save(board);
	}

	@Test
	public void 게시물단건조회() {
		// by~: 조건절
		// id : pk
		// 게시물번호를 기준으로 조회
		// 1번 게시물 조회
		// find 함수 호출하면 select 쿼리 생성
		// 함수에 by가 있으면 where 조건절 붙음
		Optional<Board> optional = repository.findById(5);
		if(optional.isPresent()) {
			Board board = optional.get();
			System.out.println(board);
		}
	}
	
	@Test
	public void 게시물목록조회() {
		// find 함수는 조회함수
		// 함수에 전달하는 인자가 없다면 조건이 없는 것
		List<Board> list = repository.findAll();
		System.out.println(list);
	}
}
