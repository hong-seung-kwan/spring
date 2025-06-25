package com.example.demo.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;

// S3 버킷 저장소에 파일을 업로드하는 유틸 클래스

@Component
public class S3FileUtil {
	
	@Autowired
	AmazonS3 amazonS3;
	
	// s3 버킷명
	String bucketName = "seungkwanbucket";
	
	// 파일 업로드
	// 매개변수: 파일 스트림
	// 반환값: 파일의경로(URL)
	public String fileUpload(MultipartFile file) {
		
		// 파일 원본 이름
		String originFilename = file.getOriginalFilename();
		
		// 파일의 확장자
		int index = originFilename.lastIndexOf(".");
		// 예: .png => png
		String extention = originFilename.substring(index + 1);
		
		// 새로운 파일명 만들기
		// 중복되지 않는 이름
		// 파일 업로드시 기존 파일과 동일한 이름의 파일이 들어올 수 있음
		String str = UUID.randomUUID().toString().substring(0,10);
		String s3filename = str + originFilename;
		
		// s3저장소에 파일 업로드
		InputStream is;
		
		try {
			// 실제 파일 데이터
			is = file.getInputStream();
			// stream -> byte
			byte[] bs = IOUtils.toByteArray(is);
			
			// 부가정보 (메타데이터)
			ObjectMetadata metadata = new ObjectMetadata();
			// 예: image/png 또는 image/jpg 또는 text
			metadata.setContentType("image"+ extention);
			// 파일의 크기
			metadata.setContentLength(bs.length);
			
			// 전송할 파일 스트림 생성
			// file 클래스 => stream
			ByteArrayInputStream stream = new ByteArrayInputStream(bs);
			
			// 아마존 S3 API 호출을 위한 Request 메세지 생성
			// 인자: 버킷명, 파일명, 파일스트림, 부가정보
			PutObjectRequest request = new PutObjectRequest(bucketName, s3filename, stream, metadata);
			
			// 파일 업로드 API 호출
			amazonS3.putObject(request);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 파일을 업로드하고 난후에 파일 URL 꺼내기
		String url = amazonS3.getUrl(bucketName, s3filename).toString();
		return url;
	}
	
}
