package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// MVC 중에서 M
// M: Model (데이터 구조를 정의하는 클래스)

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleDTO {
	
	int no; // 번호
	
	String text; // 글 내용
	
	LocalDate regDate; // 등록일
	
}
