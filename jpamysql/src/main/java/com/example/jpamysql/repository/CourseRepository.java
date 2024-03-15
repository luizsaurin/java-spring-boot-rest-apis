package com.example.jpamysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpamysql.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
}
