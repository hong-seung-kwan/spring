package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 사용자 요청 메세지에서 데이터 조회하기

@Controller
public class ParamController1 {
	
	// 사용자가 보낸 요청메시지는 함수의 매개변수로 받기
	
	@ResponseBody
	@GetMapping("/headers1")
	public String method1(HttpServletRequest request,
						  HttpServletResponse response,
						  HttpMethod method,
						  Locale locale, // 국가
		@RequestHeader MultiValueMap<String, String> headerMap, // 모든 헤더
		@RequestHeader("host") String host,
		@CookieValue(value = "myCookie", required = false) String cookie
	) {
		System.out.println("request: "+ request);
		System.out.println("response: "+ response);
		System.out.println("method: "+ method);
		System.out.println("Locale: "+ locale);
		System.out.println("headerMap: "+ headerMap);
		System.out.println("host: "+ host); // 헤더 중에서 header 추출
		System.out.println("cookie: "+ cookie); // 쿠키
		return "ok";
	} // 전체 경로: GET + localhost:8080/headers1
	
	// HttpServletRequest: 사용자가 보낸 요청 메시지
	// HttpServletResponse: 사용자에게 보낼 응답 메시지
	@RequestMapping("/headers2")
	public void method2( HttpServletRequest request,
						HttpServletResponse response) throws IOException {
		// 요청 메시지에서 파라미터 꺼내기
		String username = request.getParameter("username");
		System.out.println("username: " + username);
		
		String age = request.getParameter("age");
		System.out.println("age: " + age);
		
		// 응답 메세지 만들기
		PrintWriter writer =response.getWriter();
		writer.write("ok~~~");
	}
}
