package com.example.demo.security;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.dto.MemberDTO;

// 시큐리티에 필요한 인증 클래스 만들기
// 시큐리티가 사용자 정보를 확인할 때 사용함
public class CustomerUser extends User{

	// 생성자 함수 정의
	// MemberDTO -> User 변환 
	public CustomerUser(MemberDTO dto) {
		// dto의 데이터를 하나씩 옮기기
		// asList: 인자를 여러개 받아서 list로 생성하는 함수
		// 부모를 상속받은 자식 인스턴스를 입력할 수 있다..!
		super(dto.getId(), dto.getPassword(), Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));
	}
}
