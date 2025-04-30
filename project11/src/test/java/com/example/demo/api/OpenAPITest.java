package com.example.demo.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@SpringBootTest
public class OpenAPITest {
	
	// API 파라미터
	String key = "7eAC%2F1BemyOryl9NDkuC58gnWfv8QmVcCZfLeXyCQv6GMt1MeyyRRH8kf2HtwLOwzXGsHqaebu5Rmf05b1GOTA%3D%3D";
	String dataType = "JSON";
	String code = "11B20201";
	
	// 함수이름과 리턴타입 수정
	// 리턴값: API 응답 결과
	String getWeather() throws IOException {
		
		// 기상청 육상예보 API를 호출하여 날씨 데이터 받아오기
		
		// StringBuilder: 문자열을 생성하는 클래스
		// API 주소에 파라미터 연결하기
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("regId","UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); /*11A00101(백령도), 11B10101 (서울), 11B20201(인천) 등... 별첨 엑셀자료 참조(‘육상’ 구분 값 참고)*/      
        URL url = new URL(urlBuilder.toString());
        
        // API 호출
        // 메소드 종류: GET       
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        // 실제로 API를 호출하는 부분
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        
        // 응답 데이터 확인 (응답코드와 데이터)
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//      System.out.println(sb.toString());
		
        // API를 호출하고 응답받은 결과를 반환
		return sb.toString();
	}
	
	@Test
	void API응답데이터를_클래스로변환() throws Exception {
		
		// 함수를 사용해서 육상예보 API를 호출
		String weather =  getWeather();
//		System.out.println("날씨정보: " + weather);
		
		// json string -> class 변환
		
		// 준비
		// 매퍼 클래스 생성
		ObjectMapper mapper = new ObjectMapper();
		
		// 매퍼 클래스에서 옵션 설정
		// 분석할 수 없는 부분은 생략 (이설정을 생략하면 에러날 확률 높음)
		// cinfigure함수의 인자: 항목, on/off
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		// 파싱한 결과를 저장할 변수를 선언
		Root root = null;
		
		// 날씨 데이터 (json string)을 root 객체로 변환
		// readValue 함수의 인자: json string, 변환하고 싶은 class
		root = mapper.readValue(weather, Root.class);
		
		// 파싱한 결과를 확인
		System.out.println(root.response.body.items.item.get(0).wf);
		
	}
}
