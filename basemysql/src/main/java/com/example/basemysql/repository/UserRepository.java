package com.example.basemysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basemysql.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
