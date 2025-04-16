package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Memo;

import jakarta.transaction.Transactional;

// 리파지토리 만드는 방법
// "JpaRepository" 인터페이스 상속받기
// 제네릭 타입은 엔티티와 pk타입을 작성

// JpaRepository를 상속받아 조회,추가,수정,삭제 기능을 물려받음

// 조회는 데이터에 영향이 없음
// 하지만 추가나 삭제는 데이터에 영향이 있음
// sql를 실행한 후에 commit을 해야 db에 결과가 반영됨
@Transactional
public interface MemoRepository extends JpaRepository<Memo, Integer> {
	
	// 메모의 번호가 10에서 20사이인 데이터 검색
	// 함수 이름 작성
	// findBy~ : 조회
	// No : 기준 컬럼
	// Between: 연산자
	// Between은 2개의 파라미터를 사용함
	// 파라미터는 함수의 매개변수로 선언
	// 조회함수의 반환값: Memo 또는 List<Memo>
	List<Memo> findByNoBetween(int from, int to);
	
	// 10번 아래 메모를 검색
	// findBy~ : 조회
	// No: 기준컬럼
	// LessThan: 비교연산자 <
	// 연산자에서 사용하는 파라미터는 함수의 매개변수로 선언
	List<Memo> findByNoLessThan(int mno);
	
	
	// 내용이 없는 데이터를 검색
	// findBy~ : 조회
	// Text: 기준컬럼
	// IsNull: is null 연산자
	List<Memo> findByTextIsNull();
	
	// 메모의 번호를 기준으로 역정렬
	// 조건검색 x 정렬만
	// findAll: 전체 조회
	// OrderBy: 정렬
	// No: 기준컬럼
	// Desc: 정렬방식 (역정렬)
	List<Memo> findAllByOrderByNoDesc();
	
	// 삭제
	// 5번 아래 메모를 삭제
	// deleteMemoBy: 삭제
	// No: 기준컬럼
	// Lessthan: 비교연산자 <
	void deleteMemoByNoLessThan(int mno);
	
	// 어노테이션으로 함수 추가
	// 규칙:
	// 함수이름은 자유롭게 작성
	// 괄호 안에 sql을 직접 작성 (1.jqpl문법 2.mariadb에서 사용하는 sql)
	// jpql 문법:
	// table 이름 대신 entity 이름을 사용
	// 컬럼명 대신 변수명을 사용
	
	// 3번 아래 메모를 검색
	// 테이블명: tbl_memo 엔티티명:Memo
	// 파라미터는: 매개변수
	
	// jpql 분법으로 sql을 작성하면 실제 데이터베이스에 맞는 sql로 변환됨
	// jpql의 장점: 개발 중간에 데이터베이스가 oracle로 변경되어도
	// 쿼리를 변경할 필요가 없음
	// jpql의 단점: 테이블 대신 엔티티를 사용해야 해서 불편함
	@Query("select m from Memo m where m.no < :mno")
	List<Memo> get1(@Param("mno") int mno);
	
	// 순수한 sql의 장점: 작성하기 편함
	// 순수한 sql의 단점: 데이터베이스가 변경되면 쿼리를 수정해야함
	
	// 결론:
	// 쿼리메소드/어노테이션(jpql)/어노테이션(순수한sql)
	// 특별한 경우가 아니면 제일 쉬운 방식을 선택 => 순수한 sql
	

}
/* 
 * jpa 기능을 사용하는 방법
 * 1. 엔티티 생성 (테이블 정의)
 * 2. 리파지토리 생성 (테이블 안에 있는 데이터를 조회, 수정, 추가, 삭제 처리) 
 * */
 

// MemoRepository는 ListPagingAndSortingRepository 로부터
// 페이지 처리 함수를 물려받음
// MemoRepository는 부모로부터 CRUD기능과 페이지처리기능을 물려받음.