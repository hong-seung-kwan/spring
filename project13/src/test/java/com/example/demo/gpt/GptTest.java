package com.example.demo.gpt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

// GPT API를 호출하는 테스트 클래스
@SpringBootTest
public class GptTest {
	
	/*
	 * 변수에 값을 바로 대입하지 않고, 환경파일을 사용하는 이유
	 * 키값을 변경되지 않기 때문에 한 파일에서 관리하고, 필요하면 가져쓸 것
	 * */
	
	
	
	@Value("${apikey}")
	String API_KEY;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	
    @Test
    void APIKEY확인() {
    	
    	System.out.println("API KEY: " + API_KEY);
    	
    }
    
    
	@Test
	void GPTAPI호출() throws JSONException, IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();

        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", "안녕~~~");

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4");
        requestBody.put("messages", messages);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        String reply = jsonResponse
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");

        System.out.println("Assistant: " + reply);
	}
	
}
