package com.kh.model;

import java.util.Date;

import lombok.*;


@Getter
@Setter
public class User{
	private Long userID;
	private String username;
	private String password;
	private String fullName;
	private String email;
	private Date registrationDate;
	
	
}
