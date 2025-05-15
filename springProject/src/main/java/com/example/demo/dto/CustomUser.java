package com.example.demo.dto;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
	
	
	public CustomUser(MemberDTO dto) {
		super(dto.getUserId(), dto.getUserPw(),Arrays.asList(new SimpleGrantedAuthority(dto.getRole())));
	}
}
