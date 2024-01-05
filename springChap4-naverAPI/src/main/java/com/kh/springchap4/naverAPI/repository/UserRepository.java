package com.kh.springchap4.naverAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springchap4.naverAPI.model.UserSNS;

public interface UserRepository extends JpaRepository<UserSNS, Long> {

}
