package com.kh.springchap1.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//api 로 전달해서 호출
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true")

public class HelloController {
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello from Spring Boot!";
	}
	@GetMapping("/java")
	public String getJava() {
		return "Spring boot test code";
	}
	
	//GetMapping react 라는 엔드포인트를 주고 react에서 api 호출 
	
}