package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;

@SpringBootTest
public class CommentRepositoryTest {

	@Autowired
	CommentRepository repository;
	
	@Test
	void 리파지토리확인() {
		System.out.println("repository : " + repository);
	}
	
	@Test
	void 댓글등록() {
		// 댓글에 입력한 회원 엔티티 생성
		Member member = Member.builder().id("user1").build();
		// 게시물 엔티티 생성
		// 참조 데이터는 pk만 입력하면 됨
		Board board = Board.builder().no(1).build();
		// 엔티티 생성
		// 댓글 데이터 중에서 입력해야 하는 항목: 댓글내용, 게시물 작성자
		// 15번 게시물에 user1이 작성한 댓글
		Comment comment = Comment.builder().content("댓글2").board(board).writer(member).build();
		
		repository.save(comment);
	}
	
	@Test
	void 댓글목록조회() {
		
		List<Comment> list = repository.findAll();
		for(Comment comment : list) {
			System.out.println(comment);
		}
	}
	
	@Test
	void 댓글단건조회() {
		Optional<Comment> optional = repository.findById(1);
		if(optional.isPresent()) {
			Comment comment = optional.get();
			System.out.println(comment);
		}
	}
	
	@Test
	void 댓글수정() {
		Optional<Comment> optional = repository.findById(1);
		if(optional.isPresent()) {
			Comment comment = optional.get();
			comment.setContent("댓글수정");
			repository.save(comment);
			
			System.out.println(comment);
		}
	}
	
	@Test
	void 댓글삭제() {
		repository.deleteById(1);
	}
	
	@Test
	void 게시물별댓글목록조회() {
		List<Comment> list = repository.findByBoardNo(2);
		
		for(Comment comment : list) {
			System.out.println(comment);
		}
	}
	
	@Test
	void 댓글_삭제() {
		// 2번 게시물에 달린 모든 댓글 삭제
		repository.deleteByBoardNo(2);
	}
}
