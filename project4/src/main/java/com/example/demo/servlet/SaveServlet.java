package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 회원등록폼에서 전달받은 데이터를 등록하는 서블릿
// 서브릿의 이름과 주소는 중복 불가
@WebServlet(name = "SaveServlet",urlPatterns = "/servlet/save")
public class SaveServlet extends HttpServlet {

	// 저장소
	MemberRepository repository = new MemberRepository();
	
	// 사용자의 요청을 처리하는 함수
	// req: 사용자가 보낸 요청 메세지
	// res: 사용자가 보낼 응답 메세지
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 사용자가 보낸 파라미터(아이디와 패스워드) 꺼내기
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		
		// 새로운 회원을 생성
		Member member = Member.builder()
									.userId(userId)
									.password(password)
									.build();
		
		repository.save(member);
		
		// 응답 메세지 만들기
		// 등록 처리 화면은 반환
		
		// header
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		
		// body
		PrintWriter writer = resp.getWriter();
		
		// 처리결과를 보여줄 html 코드를 작성
		writer.write("<html>\n" +
		        "<head>\n" +
		        " <meta charset=\"UTF-8\">\n" +
		        "</head>\n" +
		        "<body>\n" +
		        "성공\n" +
		        "<ul>\n" +
		        " <li>회원번호="+member.getNo()+"</li>\n" +
		        " <li>이름="+member.getUserId()+"</li>\n" +
		        " <li>비밀번호="+member.getPassword()+"</li>\n" +
		        "</ul>\n" +
		        "</body>\n" +
		        "</html>");
	}

	
}
