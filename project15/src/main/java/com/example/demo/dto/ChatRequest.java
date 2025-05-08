package com.example.demo.dto;

import java.util.List;
import java.util.Stack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRequest {
	
	// stack: 배열, 리스트와 같은 자료 구조
	// 특징: 데이터를 꺼낼 때 제거됨
	
	// 사용자 메세지 (목록)
	List<String> userMsg;
	
	// 챗봇 응답 (목록)
	List<String> botMsg;
}