package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
