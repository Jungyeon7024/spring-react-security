package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.mapper.UsersMapper;
import com.kh.model.User;

@Service
public class UserService {
	@Autowired
	private UsersMapper usersMapper;

	//회원 정보 저장하기
	public void signUpUser(User user) {
		usersMapper.insertUser(user);
	}
}