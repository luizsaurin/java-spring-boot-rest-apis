package com.example.versioning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.versioning.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
