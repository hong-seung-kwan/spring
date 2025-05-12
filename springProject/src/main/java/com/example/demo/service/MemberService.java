package com.example.demo.service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {

	
	boolean register(MemberDTO dto);
	
	default MemberDTO entityToDto(Member member) {
		
		MemberDTO dto = MemberDTO.builder()
										.userId(member.getUserId())
										.userPw(member.getUserPw())
										.userName(member.getUserName())
										.userPhone(member.getUserPhone())
										.userAddress(member.getUserAddress())
										.role(member.getRole())
										.build();
		
		return dto;
	}
	
default Member dtoToEntity(MemberDTO dto) {
		
		Member member = Member.builder()
										.userId(dto.getUserId())
										.userPw(dto.getUserPw())
										.userName(dto.getUserName())
										.userPhone(dto.getUserPhone())
										.userAddress(dto.getUserAddress())
										.role(dto.getRole())
										.build();
		
		return member;
	}
	
}
