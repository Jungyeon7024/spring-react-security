package com.kh.springchap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springchap1.model.Users;

public interface UserRepository extends JpaRepository <Users, Long> {

}
