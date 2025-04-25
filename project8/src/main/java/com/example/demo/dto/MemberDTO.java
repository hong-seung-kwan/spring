package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 컴포넌트 사이에서 데이터를 전달하는 클래스
// Entity, DTO
// Entity는 데이터베이스로 데이터를 전달
// DTO는 컨트롤러와 뷰 사이에서 데이터를 전달

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	
	String id; // 아이디
	
	String password; // 패스워드
	
	String name; // 이름
	
	LocalDateTime regDate; // 등록일
	
	LocalDateTime modDate; // 수정일
}
