package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
