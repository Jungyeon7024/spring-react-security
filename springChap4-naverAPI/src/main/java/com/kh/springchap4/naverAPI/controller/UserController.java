package com.kh.springchap4.naverAPI.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.springchap4.naverAPI.model.UserSNS;
import com.kh.springchap4.naverAPI.repository.UserRepository;
import com.kh.springchap4.naverAPI.service.UserService;

@Controller
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "index";
	}
	//구글 로그인을 위한 URL 추가
	@GetMapping("/oauth2/authorization/google")
	public String googleLogin() {
		return "redirect:/oauth2/authorization/google";
	}
	
	//네이버 로그인을 위한 URL 추가
	@GetMapping("/oauth2/authorization/naver")
	public String naverLogin() {
		return "redirect:/oauth2/authorization/naver";
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccess(
			@AuthenticationPrincipal OAuth2User principal, 
			@RequestParam(value="naverResponse", required = false) String naverResponse,
			Model model) {
		
		userService.naverLoginService(principal, naverResponse, model);
		return "loginSuccess";
	}

	
	
	
	
}















