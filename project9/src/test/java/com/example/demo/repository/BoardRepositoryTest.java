package com.example.demo.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

// BoardRepository의 기능(데이터 추가,조회,수정,삭제)를 확인하는 클래스

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	BoardRepository repository;
	
	@Test
	void 게시물등록() {
		
		// board의 writer는 외래키이므로
		// member에 없는 id는 사용할 수 없음!
		
		// member 테이블에 있는 id만 사용 가능!
		
		// 작성자에 입력할 member 먼저 생성
		// user1 또는 user2 중에서 선택
		Member member = Member.builder().id("user2").build();
		
		// board 생성
		Board board = Board.builder()
								.title("안녕하세요~")
								.content("안녕하세요~")
								.writer(member)
								.build();
		
		// 테이블에 새로운 게시물 추가
		repository.save(board);
		
		Board board2 = Board.builder()
								.title("안녕하세요~")
								.content("안녕하세요~")
								.writer(member)
								.build();

		// 테이블에 새로운 게시물 추가
		repository.save(board2);
	}
	
	@Test
	void 데이터조회() {
		
		Optional<Board> optional = repository.findById(2);
		
		Board board = optional.get();
		System.out.println(board);
	}
	
	@Test
	void 게시물삭제() {
		// user1이 작성한 모든 게시물 삭제
		repository.deleteById(2);
		repository.deleteById(4);
	}
	
	@Test
	void user2가_작성한_게시물삭제() {
		
		// 먼저 파라미터는 member를 생성
		// member의 데이터 중에서 id만 입력할 것!(이름과 패스워드는 필요x)
		Member member = Member.builder().id("user2").build();
		repository.deleteWriter(member);
		
//		repository.deleteWriter("user2");
	}
	
	@Test
	void 게시물_삭제() {
		// 자식 테이블(comment)에서 부모테이블 참조하고 있기 때문에
		// 1번 게시물을 삭제할 수 없다
		
		// 1번 게시물에 달려있는 댓글을 먼저 삭제하고 게시물을 삭제
		
		repository.deleteById(1);
	}
}
