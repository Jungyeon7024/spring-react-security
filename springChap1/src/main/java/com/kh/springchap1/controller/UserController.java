package com.kh.springchap1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.springchap1.model.Users;
import com.kh.springchap1.repository.UserRepository;

//UserController
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8010", allowCredentials = "true")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping//("/api/user") GetMapping 뒤에 아무것도 없으면
	//위에서 작성해주는 ("/api/user") 주소로 자동으로 지정되는 것
	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users newUser){
		Users createdUser = userRepository.save(newUser);
		return ResponseEntity.ok(createdUser);
	}
}