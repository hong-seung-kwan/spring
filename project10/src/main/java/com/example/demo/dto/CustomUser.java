package com.example.demo.dto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/* 사용자 인증 클래스
 * 시큐리티는 로그인을 처리할때 User 클래스를 사용한다
 * */

public class CustomUser extends User {

	// 매개변수: DTO
	// DTO -> User 변환
	public CustomUser(MemberDTO dto) {		
		
		// 아이디, 패스워드, 권한 목록
		super(dto.getId(),dto.getPassword(),Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));
		
	}
	
}
