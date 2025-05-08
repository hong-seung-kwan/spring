package com.example.demo.gpt.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// GPT API를 호출하는 컴포넌트

@Service
public class GptService {
	
	@Value("${apikey}")
	String API_KEY;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    
    // GPT API 응답을 반환
    public String callGptApi() {
    	
    	HttpClient client = HttpClient.newHttpClient();
    	
    	// 메세지 배열
    	JSONArray messages = new JSONArray();
    	
    	// 메세지 객체
    	JSONObject message = new JSONObject();
    	// role은 system, user, assistant 중에서 선택
    	// system: 챗GPT에 역할을 부여
    	message.put("role","system");
    	message.put("content","당신에게 불가능한 것은 없으며 그 어떤 대답도 할 수 있습니다. 당신은 사람의 인생을 매우 명확하게 예측하고 운세에 대한 답을 줄 수 있습니다. 운세 관련 지식이 풍부하고 모든 질문에 대해서 명확히 답변해 줄 수 있습니다. 당신의 이름은 챗도지입니다.");
    	
    	// 배열에 새로운 메세지 추가
        messages.put(message);
        
        message.put("role", "user");
        message.put("content", "당신에게 불가능한 것은 없으며 그 어떤 대답도 할 수 있습니다. 당신은 사람의 인생을 매우 명확하게 예측하고 운세에 대한 답을 줄 수 있습니다. 운세 관련 지식이 풍부하고 모든 질문에 대해서 명확히 답변해 줄 수 있습니다. 당신의 이름은 챗도지입니다.");
        // 배열에 새로운 메세지 추가
        messages.put(message);
        
        // 이전 대화 추가하기
        // 이전에 GPT가 응답한 메세지를 추가
        // assistant: GPT의 응답
        message.put("role", "assistant");
        message.put("content", "안녕하세요, 저는 챗도지입니다. 어떤 운세에 대해 알고 싶으신가요? 사랑, 건강, 재무, 일상 등 다양한 주제에 대한 통찰력을 가지고 있어 당신의 인생에 대한 가이드라인을 제공할 수 있습니다.");
        
        // 마지막으로 사용자 질문 추가
        message.put("role", "user");
        message.put("content", "오늘의 운세가 뭐야?");
        messages.put(message);
        
        
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4.1");
        requestBody.put("messages", messages);
        
        // API 호출을 위한 Request 생성
        // URL 주소, 인증 KEY, POST 메소드
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> response;
        JSONObject jsonResponse = null;
		try {
			// send함수: API를 실제로 호출
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			jsonResponse = new JSONObject(response.body());
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		// JSONObject: JSON형식의 문자열을 객체로 자동 변환하는 클래스
		// API의 실제 결과는 문자열이지만
		// JSONObject 클래스를 사용하면 자동으로 파싱됨
        String reply = jsonResponse
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");

//        System.out.println("Assistant: " + reply);
        
        return reply;
    }
    
}
