<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>

<!-- jstl: jsp에서 java code를 사용할때 쓰는 문법 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 회원 등록 처리 결과를 보여주는 화면 -->
	
	<!-- 컨트롤러에서 전달받은 데이터를 화면에 표시 -->
	<c:out value="${member.no}"/> 번째 <c:out value="${member.userId}"/> 회원을 추가했습니다!
</body>
</html>