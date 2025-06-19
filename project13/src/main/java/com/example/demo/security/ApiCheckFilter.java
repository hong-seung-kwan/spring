package com.example.demo.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

	// UserDetailsService를 빈으로 직접 주입받지 않는 이유:
	// Check 필터와 UserDetailsService는 동시에 생성이 되기 때문에
	// Check 필터에서 Service를 주입받을 수 없음
	UserDetailsService userDetailsService;

	// 생성자 선언
	// 필요한 인스턴스를 외부에서 주입받기
	public ApiCheckFilter(UserDetailsService detailsService) {
		jwtUtil = new JWTUtil();
		this.userDetailsService = detailsService;
	}

	// API에 인증정보(토큰)가 포함되어있는지 확인
	// 로그인과 회원가입은 인증없이 API 사용 가능
	// 게시물과 회원 관련 API는 인증 필요

	// /board/* /member/* API가 호출될 때
	// 인증키가 있는지 확인하여 인증을 처리하는 필터
	// 순서: 게시물 등록 API 호출 > CHECK 필터 > 통과 > API 실행
	
	// /board/* /member/* API는 검사를 진행
	// 그외 /login /register는 생략
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("ApiCheckFilter...!");
		System.out.println("ApiCheckFilter...!");
		System.out.println("ApiCheckFilter...!");

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
				// 요청 메세지에 인증키가 포함되어 있는지 확인
				boolean check = checkAuthHeader(request);

				if (check) {

					// 이후에 인증 상태를 유지할 수 있도록
					// 인증객체를 생성하여 전달하기
					// 요청 헤더에서 토큰에 포함된 사용자 아이디 추출
					String username = getUserId(request);

					// 인증서비스를 이용해 인증 객체 생성
					UserDetails details = userDetailsService.loadUserByUsername(username);

					// 인증 객체 -> 인증 정보
					// 생성자 인자: 인증객체, 패스워드(x), 권한
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(details,
							null, details.getAuthorities());

					// 인증 정보에 부가 정보 추가
					// 요청 메세지에 포함된 클라이언트의 IP주소, 세션ID 등
					WebAuthenticationDetails webDetails = new WebAuthenticationDetailsSource().buildDetails(request);

					// 인증 정보에 부가 정보 추가
					authToken.setDetails(webDetails);

					// 마지막으로 시큐리티 컨테이너에 인증정보 담기
					SecurityContextHolder.getContext().setAuthentication(authToken);

					// 이후에도 인증 상태를 유지하므로, 나중에 로그인한 사람의 아이디를 사용할 수 있음

					// 검사에 성공했으면 다음필터를 이어서 실행
					filterChain.doFilter(request, response);
					return;
				} else {
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
		// 검사를 진행하는 경우에는 위에 있는 return 키워드를 만남
		// 그렇지 않은 경우에는 함수가 끝나기 전에 처리
		
		// /login /register는 검사만 생략하고 다음 필터를 진행
		// 이게 없으면 필터체인이 중간에 종료가 되서, API 호출 안됨
		filterChain.doFilter(request, response);
	}

	// 함수 추가
	// 사용자 요청 메세지에서 토큰을 꺼내서 검사하는 함수
	// 매개변수: 사용자 요청 메세지
	// 리턴값: 검사 결과
	boolean checkAuthHeader(HttpServletRequest request) {

		// 요청메세지 헤더에서 토큰 꺼내기
		String auth = request.getHeader("Authorization"); // key
		System.out.println("Authorization: " + auth);

		if (auth != null) {

			// 토큰이 유효한지 검사
			String id = null;
			try {
				id = jwtUtil.validateAndExtract(auth);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 토큰 검사에 성공했으면 id 반환
			if (id != null) {
				return true;
			}

		}

		return false;
	}

	// 새로운 함수 추가
	// 요청메세지에서 토큰을 꺼내고, 토큰에서 아이디를 추출하는 함수
	// 매개변수: 요청메시지
	// 리턴값: 사용자 아이디
	String getUserId(HttpServletRequest request) {

		// 먼저 요청메세지의 헤더에서 토큰 꺼내기
		String auth = request.getHeader("Authorization");

		if (auth != null) {
			try {
				String id = jwtUtil.validateAndExtract(auth);
				return id;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}
