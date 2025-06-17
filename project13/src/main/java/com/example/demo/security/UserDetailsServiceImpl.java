package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

// 로그인 처리 클래스

public class UserDetailsServiceImpl implements UserDetailsService {
	
	// 로그인에 필요한 서비스 추가
	@Autowired
	MemberService service;
	
	// 사용자의 아이디를 전달받아 유효성 확인하는 함수
	// 로그인 함수
	
	// 매개변수: username(아이디)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 해당 회원이 존재하는지 확인
		MemberDTO dto = service.read(username);
		
		if(dto == null) {
			throw new UsernameNotFoundException("fail..");
		}else {
			// dto -> user 변환하여 인증객체를 반환
			return new CustomerUser(dto);
		}
		
	}

}
