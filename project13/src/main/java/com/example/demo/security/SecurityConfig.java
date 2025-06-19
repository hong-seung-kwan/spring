package com.example.demo.security;
import com.example.demo.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.MemberService;

// 시큐리티 설정 클래스
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// 필터를 빈으로 등록
	// 이제 자동으로 필터체인에 새로운 필터가 추가됨
	// check 필터를 생성할 때, 필요한 UserDetailsService 주입
	@Bean
	ApiCheckFilter apiCheckFilter() {
		return new ApiCheckFilter(customUserDetailsService());
	}
	
	// SecurityFilterChain: 사용자 인증 절차
	// 우리 사이트에 맞는 인증 절차를 직접 설정하여 필터체인을 생성
	
	// 인증서비스 빈으로 등록
	// 시큐리티가 동작할때 컨테이너에 있는 인증서비스를 꺼내서 사용함
	// 언제? 사용자가 로그인을 시도할때
	@Bean
	UserDetailsService customUserDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	// 패스워드인코더를 빈으로 등록
	// 패스워드인코더란? "1234" -> "awdasda" 해시코드로 변환
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCrypt: 암호화 인코더 중에서 단방향 인코더
		return new BCryptPasswordEncoder();
	}
	
	// member service 인스턴스를 반환하는 함수 선언
	// 함수를 호출하면 바로 인스턴스를 반환하고, 빈으로도 등록
	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl();
	}
	
	// 우리 사이트에 맞게 필터체인 설정
	// @Bean: 함수에서 반환된 객체를 컨테이너에 빈으로 등록
	// 빈으로 등록된 필터체인은 시큐리티가 동작할때 실행됨
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 사이트 구조: API
		// 폼로그인 비활성화
		http.formLogin().disable();
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// csrf 비활성화
		// csrf:
		// 해커가 post나 put 요청시 바디에 악의적인 내용을 담아서 전달
		// 시큐리티는 이를 막기 위해 csrf 토큰을 발급하고, 화면에 토큰이 없으면
		// post나 put 요청을 할 수 없도록 막음
		// 개발자 입장에서는 항상 토큰을 만들고 화면에 담아야하기 때문에 귀찮음
		http.csrf().disable();
		
		// URL 주소에 따라 접근 권한 처리
		// 로그인 API는 아무나 사용 가능
		http.authorizeHttpRequests()
			.requestMatchers("/login", "/register", "/board/*", "/member/*")// 주소
			.permitAll(); // 권한부여
		
		// 필터 순서 변경
		// 첫번째 인자: apicheck
		// 두번째 인자: username
		// username filter 보다 apicheck filter가 더 먼저 실행되도록
//		http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
		
		// 로그인 필터 등록
		// 1. 인증 매니저 설정
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		// 인증 매니저에 필요한 객체를 주입
		builder.userDetailsService(customUserDetailsService())
				.passwordEncoder(passwordEncoder());
		
		// 2. 인증 매니저 생성
		AuthenticationManager manager = builder.build();
		
		// 3. 시큐리티 인증 매니저 등록
		http.authenticationManager(manager);
		
		// 로그인 필터를 등록하기 위한 준비중...
		
		// 4. 로그인 필터 생성
		// 인자로 로그인 api를 호출할때 사용할 URL 주소 입력
		// 빈 대신 서비스 인스턴스를 직접 만들어서 넣기
		ApiLoginFilter loginFilter = new ApiLoginFilter("/login", memberService());
		
		// 5. 로그인 필터에 인증매니저 주입
		loginFilter.setAuthenticationManager(manager);
		
		// 6. 필터 순서 변경
		// login 필터가 username보다 더 먼저 실행되어야함
		http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
		
		// 필터 순서: login > apicheck > username
		http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}



// STATELESS: 서버에서 세션 상태 관리 안함
// API는 대체로 서버에서 세션 상태를 관리하지 않는다

// 로그인 인증 방식:
// 세션 방식 OR 토큰 방식

// 1. 세션 방식
// 주로 화면을 구현하는 사이트에서 사용함
// 절차:
// 사용자가 로그인 폼에 아이디와 패스워드를 입력
// 서버는 세션ID를 생성하고, 이를 사용자의 브라우저에 전달
// 이후 사용자가 다른 페이지를 요청하면, 브라우저가 세션ID를 함께 전달
// 따라서 서버는 세션ID를 보관하여 사용자 인증 정보를 관리

// 2. 토큰 방식
// 주로 Restful API에서 사용함
// 절차:
// 사용자가 로그인 API를 호출
// 서버는 토큰을 생성하고, 이를 사용자에게 전달(브라우저 또는 POSTMAN)
// 사용자는 토큰을 브라우저의 로컬 스토리지에 저장
// 이후 사용자가 다른 API를 요청할때, 헤더에 토큰을 담아서 전달
// 따라서 서버는 세션 상태를 관리할 필요가 없으므로, 부담(부하)이 줄어듬

// API에서 토큰 방식으로 주로 사용하는 이유:
// 토큰 방식은 STATELESS로 서버에서 세션 상태를 관리할 필요가 없음
// 트래픽이 큰 사이트에서는 API를 여러개 만들어서 사용자 요청을 처리하는 경우가 있음
// 같은 API를 여러개 복제하여 관리하는 방식으로 로드밸런싱을 통해 사용자 요청을 처리함
// 이때, 사용자의 요청이 어떤 서버로 전달될지 알수가 없음
// 그런데 세션방식을 사용하면 반드시 같은 서버로만 요청이 전달되어야함
// 하지만 토큰방식을 사용하면 모든 서버가 사용자 요청을 처리할 수 있음