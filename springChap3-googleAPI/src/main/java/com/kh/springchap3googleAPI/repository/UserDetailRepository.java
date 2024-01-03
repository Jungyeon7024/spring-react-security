package com.kh.springchap3googleAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kh.springchap3googleAPI.model.UserGoogle;

public interface UserDetailRepository extends JpaRepository<UserGoogle, Long>{
	// 추가적으로 필요한 메서드 작성 
	Optional<UserGoogle> findByUsername(String username);
}
