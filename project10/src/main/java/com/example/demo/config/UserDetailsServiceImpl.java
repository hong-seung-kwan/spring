package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomUser;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

// 로그인 처리를 위한 클래스

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	MemberService service;
	
	// 로그인을 시도하면 인증서비스의 loadUserByUsername 함수가 호출됨
	// 매개변수: username(아이디)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		// 해당 아이디가 존재하는지 확인
		MemberDTO dto = service.read(username);
		// 회원이 존재하지 않는다면 로그인 실패
		if(dto == null) {
			throw new UsernameNotFoundException(username); // 에러 발생시키기
		}else {
			// 회원이 존재한다면 인증객체를 만들어서 반환
			// 반환된 인증객체는 스프링 컨테이너에서 보관하다가
			// 필요할때 꺼내줌
			return new CustomUser(dto);
		}

	}
	
}
