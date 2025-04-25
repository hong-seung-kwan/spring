package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

//JpaRepository 상속받기
//엔티티와 pk타입 지정

//public interface BoardRepository extends JpaRepository<Board, Integer> { 
//}

//QuerydslPredicateExecutor 상속 추가
// 추가, 수정, 삭제 시 commit 처리
@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor<Board> {
	
	// 특정 회원이 작성한 게시물을 삭제하는 메소드
	
	// 메소드를 추가하는 방법
	// 쿼리어노테이션을 사용하여 메소드를 추가
	// 규칙:
	// 메소드 이름은 자유롭게 작성
	// jpql 또는 순수한 sql을 사용
	// 파라미터는 매개변수에 선언
	
	// 순수한 sql로 기능 구현
//	@Modifying
//	@Query(nativeQuery = true, value = "DELETE FROM TBL_BOARD WHERE writer_id = :id")
//	public void deleteWriter(@Param("id") String id);
	
	// jpql로 기능 구현
	// 규칙: table 대신 entity를 사용
	// 컬럼 대신 필드를 사용
	// 파라미터도 컬럼이 아닌 필드의 타입을 사용해야함
	@Modifying
	@Query("DELETE FROM Board WHERE writer = :member")
	public void deleteWriter(@Param("member") Member member);
}
