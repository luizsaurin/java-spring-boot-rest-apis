package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
}
