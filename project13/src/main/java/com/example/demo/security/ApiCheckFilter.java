package com.example.demo.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;

// API 요청이 들어오면
// 인증정보가 포함이 되어있는지 확인하는 필터

// 필터를 완성한 후에 필터체인에 추가

// 필터체인에 새로운 필터(ApiCheckFilter)를 추가하여
// API를 호출할때마다 ApiCheckFilter가 실행됨

public class ApiCheckFilter extends OncePerRequestFilter {

	// 인증에 필요한 필드 선언 (패턴 검사기)
	AntPathMatcher matcher;
	
	JWTUtil jwtUtil;
	
	// 생성자 선언
	public ApiCheckFilter() {
		jwtUtil = new JWTUtil();
	}
	
	// API에 인증정보(토큰)가 포함되어있는지 확인
	// 로그인과 회원가입은 인증없이 API 사용 가능
	// 게시물과 회원 관련 API는 인증 필요
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 인증이 필요한 API 목록
		// 로그인과 회원가입을 제외한 나머지
		String[] patternArr = { "/board/*", "/member/*" };

		matcher = new AntPathMatcher();

		// URL 경로를 검사하여, 인증이 필요한 요청만 찾기
		// /login /register는 인증 생략
		// 나머지는 인증 진행

		for (String patt : patternArr) {

			// 패턴 검사기를 사용하여 URL 주소 검사
			// 첫번째 인자: 배열의 요소
			// 두번째 인자: 사용자 요청에 포함된 URL 주소
			// 두 주소를 비교하여 같으면 true
			// 사용자가 요청한 주소가 배열에 포함되어 있는지 확인
			// 이때, 메세지 출력
			boolean result = matcher.match(patt, request.getRequestURI());

			if (result == true) {
				
				// 토큰 검사
				boolean check = checkAuthHeader(request);
				
				if(check) {
					// 검사에 성공했으면 다음필터를 이어서 실행
					filterChain.doFilter(request, response);
					return;
				}else {
					// 실패했으면 필터 체인을 종료
					// API 호출시 토큰이 없으면 200 ok가 아닌 403 에러를 반환해야함
					
					// 응답메세지 만들어서 반환
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					response.setContentType("appilication/json; charset=utf-8");
					
					// 바디데이터 생성
					JSONObject json = new JSONObject();
					json.put("code", "403");
					json.put("message", "토큰을 넣어주세요");
					
					// 응답메세지에 데이터 담기
					PrintWriter out = response.getWriter();
					out.print(json);
					return;
				}
				
			}
		}

	}

	// 함수 추가
	// 사용자 요청 메세지에서 토큰을 꺼내서 검사하는 함수
	// 매개변수: 사용자 요청 메세지
	// 리턴값: 검사 결과
	boolean checkAuthHeader(HttpServletRequest request) {
		
		// 요청메세지 헤더에서 토큰 꺼내기
		String auth = request.getHeader("Authorization"); //key
		System.out.println("Authorization: " + auth);
		
		if(auth != null) {
			
			// 토큰이 유효한지 검사
			String id = null;
			try {
				id = jwtUtil.validateAndExtract(auth);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 토큰 검사에 성공했으면 id 반환
			if(id != null) {
				return true;
			}
	
		}
		
		return false;
	}

}
