package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Board;

// 게시물 데이터를 처리하는 클래스

// 리파지토리 만드는 방법
// jpa 상속받기
// 재네릭 타입 수정(인티티, pk)
public interface BoardRepository extends JpaRepository<Board, Integer> {

}

/*
 * BoardRepository는 부모만 JpaRepository로부터 기능을 상속받음.
 * save find delete 등 함수를 사용할 수 있음.
 * 함수를 사용하여 board 테이블에 있는 데이터를 조회하거나 수정할 수 있음.
 * 
 * 리파지토리가 생성되는 과정
 * 1. JpaRepository 상속 (수동)
 * 2. BoardRepository의 구현 클래스 생성 (자동)
 * 3. 구현체의 인스턴스를 생성 (자동)
 * 4. 빈으로 등록 (자동)
 */
