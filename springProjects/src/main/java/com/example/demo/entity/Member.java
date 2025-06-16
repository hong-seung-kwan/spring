package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Member {
	
	@Id
	@Column(length = 50, nullable = false)
	String userId;
	
	@Column(length = 200, nullable = false)
	String userPw;
	
	@Column(length = 50, nullable = false)
	String userName;
	
	@Column(length = 200, nullable = false)
	String userAddress;
	
	@Column(length = 20, nullable = false)
	String userPhone;
	
	@Column(length = 20, nullable = false)
	String role;
}
