package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
