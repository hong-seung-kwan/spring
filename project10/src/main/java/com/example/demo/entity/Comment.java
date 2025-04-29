package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// entity: 테이블 구조를 정의하는 클래스
// BaseEntity를 상속받아 등록일과 수정일 컬럼 추가
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_comment") // 테이블명
public class Comment extends BaseEntity {
	
	// pk + auto increment
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int commentNo; // 댓글 번호
	
	// 외래키 만드는 방법
	// 1. 자료형을 부모타입으로 설정
	// 2. 관계 차수 설정
	@ManyToOne
	Board board;// 게시물
	
	@Column( length = 1500 )
	String content;// 댓글 내용
	
	// 외래키 만드는 방법
	// 1. 자료형을 부모 테이블(엔티티)로 설정
	// 2. 관계차수 설정
	@ManyToOne
	Member writer;// 작성자
	
}

/* 
 * 게시물이 없으면 댓글을 작성할 수 없다
 * => 댓글 테이블의 게시물번호는 게시물 테이블을 참조
 * 
 * 회원가입을 하지 않았으면 댓글을 작성할 수 없다
 * => 댓글 테이블의 작성자는 회원 테이블을 참조
 * */
 