package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomUser;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	MemberService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberDTO dto = service.read(username);
		
		if(dto == null) {
			throw new UsernameNotFoundException(username);		
		} else {			
			return new CustomUser(dto);
		}				
	}

}
