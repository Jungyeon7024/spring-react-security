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

@Controller
public class UserController {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserController (UserRepository userRepository) {
		this.userRepository =userRepository;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "index";
	}
	@GetMapping("/oauth2/authorization/google")
	public String googleLogin() {
		return "redirect:/oauth2/authorization/google";
	}
	
	@GetMapping("/oauth2/authorization/naver")
	public String naverLogin() {
		return "redirect:/oauth2/authorization/naver";
	}
	

	
	@GetMapping("/loginSuccess")
	public String loginSuccess(@AuthenticationPrincipal OAuth2User principal,
							   @RequestParam(value="naverResponse",required = false) String naverResponse ,Model model) {
		System.out.println("OAuth2User Attribute : " + principal.getAttributes());
		String name = null;
		String email = null;
		
		if(naverResponse !=null) {
			JsonNode responseNode;
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				responseNode = objectMapper.readTree(naverResponse).get("response");
				
				if(responseNode !=null) {
					name = responseNode.get("name").asText();
					email = responseNode.get("email").asText();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(name == null||email == null) {
			String principalName =principal.getName();
			
			String[] keyValue = principalName.replaceAll("[{}]", "").split(",");
			for(String pair : keyValue) {
				String[] entry = pair.split("=");
				if(entry.length==2) {
					String key = entry[0].trim();
					String value = entry[1].trim();
					if("name".equals(key)) {
						name = value;
					}else if("email".equals(key)) {
						email =value;
					}
				}
			}
		}
		
		// 데이터 베이스 저장

		
		
		
		String provider =principal.getName();
		System.out.println("UserController 82 ↓");
		System.out.println("String principalName =principal.getName():" + provider);
		//1. model
		UserSNS user = new UserSNS();
		user.setName(name);
		user.setEmail(email);
		user.setProvider(provider);
		

		userRepository.save(user);
		
		model.addAttribute("name", principal.getAttribute("name"));
		model.addAttribute("email", principal.getAttribute("email"));
		
		
		model.addAttribute("naverResponse",naverResponse);
		return "loginSuccess";
		
	}
}



















