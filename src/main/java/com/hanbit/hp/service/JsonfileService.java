package com.hanbit.hp.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonfileService {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	
	public <T> T getJsonFile(String filePath, Class<T> classType) throws Exception {
		/* 즉 제이슨 파일에 접근하기 위해서 클래스 패스에 접근하기 위한것 */
		/*네트워크 인풋 스트림은 바이트 읽어 들이면 클리이언트
		 * 그래서 파일 읽어오는 것이나 받는 것을 칭함 바이트로 받는다.*/
		InputStream jsonStream = getClass().getClassLoader().getResourceAsStream(filePath);
		/*리드밸류는 메소드 읽는것 */
		return mapper.readValue(jsonStream, classType);
	}
	

}
