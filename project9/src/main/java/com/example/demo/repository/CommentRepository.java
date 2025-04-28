package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Comment;

import jakarta.transaction.Transactional;

// Repository: 조회, 추가, 수정, 삭제 SQL을 생성하는 컴포넌트

/* 리파지토리 만드는 방법
 * 1. JPA 상속
 * 2. ENTITY와 PK 타입 설정
 * */
 
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	// 게시물별 댓글 목록 조회
	@Query(value = "SELECT * FROM TBL_COMMENT WHERE BOARD_NO = :boardNo", nativeQuery = true)
	List<Comment> findByBoardNo(@Param("boardNo") int boardNo);
	
	// 게시물별 댓글 삭제
	@Modifying
	@Query(value = "DELETE FROM TBL_COMMENT WHERE BOARD_NO = :boardNo", nativeQuery = true)
	void deleteByBoardNo(@Param("boardNo") int boardNo);
}
