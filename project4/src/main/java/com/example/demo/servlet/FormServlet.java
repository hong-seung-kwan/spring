package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 서블릿이란? 사용자가 요청을 보내면 응답을 보내주는 클래스
// 회원 등록 폼을 전송하는 서블릿

// 서블릿 만들기
// 이름과 주소는 자유롭게 작성. 단 다른 서블릿과 중복 안됨.
@WebServlet(name = "FormServlet", urlPatterns = "/servlet/form")
public class FormServlet extends HttpServlet {
	
	// 사용자의 요청을 처리하는 메소드
	// req: 사용자가 보낸 request message를 담겨있는 객체
	// resp: 사용자에게 보낼 response message를 만드는 객체
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		// 사용자에게 보낼 응답 메세지 만들기
		// header (컨텐츠 타입과 문자 인코딩)
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		// body (회원 정보를 입력할 수 있는 html 파일)
		
		// 응답 데이터를 작성하기 위한 객체 생성
		PrintWriter writer = resp.getWriter();
		
		// html 코드를 작성
		writer.write("<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				" <meta charset=\"UTF-8\">\n" +
				" <title>회원 등록</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"<form action=\"/servlet/save\" method=\"post\">\n" + // 폼을 전송할 주소
				" 아이디: <input type=\"text\" name=\"userId\" />\n" + // 이름 입력 필드
				" 암호: <input type=\"text\" name=\"password\" />\n" + // 패스워드 입력 필드
				" <button type=\"submit\">전송</button>\n" + // 전송 버튼
				"</form>\n" +
				"</body>\n" +
				"</html>\n");
	}

	
	
}

