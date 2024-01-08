package com.kh.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.model.User;

@Mapper
public interface UsersMapper {
	void insertUser(User user);
}
