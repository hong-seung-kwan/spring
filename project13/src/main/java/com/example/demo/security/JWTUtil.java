package com.example.demo.security;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.log4j.Log4j2;

// JWT 생성, 유효성 검증

@Log4j2
public class JWTUtil {

	private String secretKey = "zerock12345678";

	// 토큰 유효기간: 1month
	// 정상적으로 발급받은 토큰이라도 유효기간이 만료되면 사용할 수 없음
	private long expire = 60 * 24 * 30;

	// 로그아웃된 토큰 리스트
	private Set<String> blacklist = new HashSet<>();

	// 로그아웃시 블랙 리스트에 추가
	public void invalidateToken(String token) {
		blacklist.add(token);
	}

	// 블랙 리스트에 포함되어 있는지 확인
	public boolean isTokenInvalid(String token) {
		return blacklist.contains(token);
	}
	
	// 토큰: 만료기간, 시크릿키 포함
	// 유효한 토큰이더라도 만료기간이 넘었으면 사용할 수 없음
	
	// 토큰을 생성하는 메소드
	public String generateToken(String content) throws Exception {

		return Jwts.builder().setIssuedAt(new Date())
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant())).claim("sub", content)
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")).compact();
	}
	
	// 사용자가 전달한 토큰에 시크릿키가 포함이 되어있지 않으면
	// 유효성 체크 실패
	// 토큰에서 아이디를 추출하는 메소드
	public String validateAndExtract(String tokenStr) throws Exception {

		String contentValue = null;
 
		for (String token : blacklist) {
			if (token.equals(tokenStr)) {
				log.info("해당 토큰을 사용할 수 없습니다..");
				return "";
			}
		}

		try {
			DefaultJws defaultJws = (DefaultJws) Jwts.parser().setSigningKey(secretKey.getBytes("UTF-8"))
					.parseClaimsJws(tokenStr);

			log.info(defaultJws);

			log.info(defaultJws.getBody().getClass());

			DefaultClaims claims = (DefaultClaims) defaultJws.getBody();

			log.info("------------------------");

			contentValue = claims.getSubject();

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			contentValue = null;
		}
		return contentValue;
	}

}