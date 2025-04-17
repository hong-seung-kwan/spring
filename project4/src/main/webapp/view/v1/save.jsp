<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- 자바코드에서 필요한 class를 import -->
<%@ page import="com.example.demo.domain.MemberRepository"%>
<%@ page import="com.example.demo.domain.Member"%>
<!-- jsp에 자바코드 작성하기 -->
<!-- 입력폼에서 전달받은 회원데이터를 저장소에 저장 -->
<%
	MemberRepository repository = new MemberRepository();
	
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	
	Member member = Member.builder().userId(userId).password(password).build();
	
	Member newMember = repository.save(member);
%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 처리 결과를 출력 -->
	<%= newMember.getNo() %> 번째 <%= newMember.getUserId() %> 회원을 추가했습니다!
	
</body>
</html>