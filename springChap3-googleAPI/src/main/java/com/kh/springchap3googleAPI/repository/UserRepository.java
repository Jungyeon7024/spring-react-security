package com.kh.springchap3googleAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springchap3googleAPI.model.UserGoogle;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserGoogle, Long> {
    Optional<UserGoogle> findByUsername(String username);
}
