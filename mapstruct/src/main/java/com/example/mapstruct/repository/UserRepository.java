package com.example.mapstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mapstruct.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
