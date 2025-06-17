package com.example.demo.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;

// 사용자의 아이디와 패스워드를 받아서, 토큰을 발급하는 필터
// 로그인 처리

// config filter service(bean)이 생성되는 시점
// 동시에.. 생성됨..
// 따라서 필터에 service를 주입받을 수 없음


// 1. 사용자가 로그인 API를 호출

// 2. ApiLoginFilter 실행
// attemptAuthentication 함수에서 임시 인증 토큰 생성
// 3. UserDetailsService 실행
// loadUserByUsername 함수를 실행하여 데이터베이스에 아이디가 있는지 확인
// 성공시 인증객체 반환
// 4. ApiLoginFilter 실행
// successfulAuthentication 함수 실행
// 전달받은 인증객체를 사용하여 토큰 발급하고 응답메세지 생성

public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

	// 로그인 처리에 필요한 필드 선언
	JWTUtil jwtUtil;

	MemberService memberService;
	
	// 생성자가 호출될때, 외부에서 서비스 주입받기
	// 생성자의 매개변수: 로그인 URL
	public ApiLoginFilter(String defaultFilterProcessesUrl, MemberService service) {
		super(defaultFilterProcessesUrl);
		jwtUtil = new JWTUtil(); // 초기화
		this.memberService = service;
	}
	
	// 로그인 요청이 들어오면 실행되는 함수
	// 매개변수: request(사용자 요청)
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		
		// 로그인 요청이 들어오면 바디에서 로그인 데이터 추출
		String body = getBody(request);
		System.out.println("login: " + body);
		
		// string -> object 변환
		ObjectMapper mapper = new ObjectMapper();
		// 첫번째 인자: string
		// 두번째 인자: 구조체
		HashMap<String, String> map = mapper.readValue(body, HashMap.class);
		// MAP에서 ID와 PW 꺼내기
		String id = map.get("id");
		String password = map.get("password");
		
		System.out.println("user id: " + id);
		System.out.println("user password: " + password);
		
		// 사용자의 아이디가 존재하는지 확인
		if(id == null) {
			throw new BadCredentialsException("id가 없습니다..");
		}
		
		// 인증 토큰 생성(jwt 아님)
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password);
		
		// 인증 매니저에 토큰을 전달
		// 사용자에게 jwt 토큰 반환하는 거 아님
		// 인증매니저 내부에서 사용하는 토큰을 만들어서 전달하는 거
		return getAuthenticationManager().authenticate(token);
	}

	// 매개변수: 요청 메세지
	// 반환값: 바디데이터
	// 전달받은 요청메세지에서 바디데이터를 추출
	public String getBody(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = request.getReader();
			char[] charBuffer = new char[128];
			int bytesRead;
			while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
				stringBuilder.append(charBuffer, 0, bytesRead);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		return stringBuilder.toString();
	}

	// attemptAuthentication 함수를 통해 1차적으로 인증 처리
	// 인증에 성공했으면 successfulAuthentication 함수를 통해 토큰 발급
	// 로그인에 성공시, 후처리
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// 로그인에 성공한 사용자의 아이디 꺼내기
		String id = authResult.getName();
		
		// 아이디를 사용하여 토큰 생성
		String token = null;
		try {
			token = jwtUtil.generateToken(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 사용자 정보 조회
		MemberDTO dto = memberService.read(id);
		
		// 화면단에서 활용할 수 있도록 부가정보 추가
		
		// 응답 데이터 만들기
		HashMap<String, Object> data = new HashMap<>();
		
		// 토큰 추가
		data.put("token", token);
		
		// 회원 데이터 추가
		data.put("user", dto);
		
		// 응답 메세지 헤더 설정
		// 바디데이터 타입과 인코딩
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		// map -> json string 반환
		// dto 클래스에 포함된 date 때문에 변환 과정에서 에러남
		ObjectMapper mapper = new ObjectMapper()
								.registerModule(new JavaTimeModule())
								.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
		
		String result = mapper.writeValueAsString(data);
		
		// 응답 메세지 전송
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	// 로그인 실패시 처리
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		// 응답메세지 만들기
		// 응답코드: 403 -> 401
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
		// 바디데이터 타입 설정
		response.setContentType("application/json; charset = utf-8");
		
		// mapper 없이 json 데이터 만들기
		// 에러코드와 에러메세지 담기
		// JSONObject 클래스를 사용하면 map 생성 -> mapper 변환 할 필요 없음
		JSONObject json = new JSONObject();
		json.put("code", "401");
		json.put("message",failed.getMessage());
		
		// 응답메세지에 바디데이터 담기
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	
	
}
