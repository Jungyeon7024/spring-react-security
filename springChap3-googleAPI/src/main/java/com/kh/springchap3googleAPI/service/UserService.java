package com.kh.springchap3googleAPI.service;


import com.kh.springchap3googleAPI.model.UserGoogle;

public interface UserService {
	 UserGoogle findByUsername(String username);
	    void saveUser(UserGoogle user);
}