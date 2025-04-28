package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 컨트롤러와 뷰 사이에서 데이터를 주고받을 때 사용하는 클래스

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
	
	int commentNo; // 댓글 번호 - auto increment
	
	int boardNo; // 게시물 번호 - 게시물 상세화면에서 찾기
	
	String content; // 댓글 내용
	
	String writer; // 작성자 - 로그인
	
	LocalDateTime regDate; // 등록일 - jpa
	
	LocalDateTime modDate; // 수정일 - jpa
}
