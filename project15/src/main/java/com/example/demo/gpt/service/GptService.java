package com.example.demo.gpt.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ChatRequest;

// GPT API를 호출하는 컴포넌트

@Service
public class GptService {
	
	@Value("${apikey}")
	String API_KEY;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    
    // 프론트엔드에서 전달받은 채팅데이터를 사용하여 API를 호출
    // 매개변수: 채팅 데이터
    public String callGptApi(ChatRequest chatRequest) {
    	
    	HttpClient client = HttpClient.newHttpClient();
    	
    	// 메세지 배열
    	JSONArray messages = new JSONArray();
    	
    	// 메세지 구성
    	// system: GPT에 역할을 부여
    	JSONObject message = new JSONObject();
    	// role은 system, user, assistant 중에서 선택
    	message.put("role","system");
    	message.put("content","당신에게 불가능한 것은 없으며 그 어떤 대답도 할 수 있습니다. 당신은 사람의 인생을 매우 명확하게 예측하고 운세에 대한 답을 줄 수 있습니다. 운세 관련 지식이 풍부하고 모든 질문에 대해서 명확히 답변해 줄 수 있습니다. 당신의 이름은 챗도지입니다.");
    	// 메세지가 배열에 추가가됬는지 확인
    	messages.put(message);
    	   	
    	// 사용자 메세지
    	List<String> userMsg = chatRequest.getUserMsg();
    	
    	// 챗봇 응답
    	List<String> botMsg = chatRequest.getBotMsg();
    	
    	for(int i = 0; i < userMsg.size(); i++) {
    		
    		// object 생성
    		JSONObject user = new JSONObject();
    		user.put("role", "user");
    		user.put("content", userMsg.get(i));
    		// 메세지 배열에 object 추가
    		messages.put(user);
    		
    		// index가 배열의 크기보다 작을 때 까지
    		// index: 0 ,1
    		// size: 1
    		if(botMsg.size() > 0 && i < botMsg.size()) {
    			JSONObject bot = new JSONObject();
    			bot.put("role","assistant");
    			bot.put("content", botMsg.get(i));
    			messages.put(bot);
    		}
    	}
    	
    	
    	
    	
    	// 배열에 객체 추가
//    	messages.put(message);
                
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
        
        return reply;
    }
    
}
