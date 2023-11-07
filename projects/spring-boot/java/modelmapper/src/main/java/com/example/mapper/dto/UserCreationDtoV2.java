package com.example.mapper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserCreationDtoV2 {

	@NotBlank
	private String name;

	@NotNull
	private Integer age;

	@NotBlank
	@Email
	private String email;

	@NotNull
	private String active;

	public UserCreationDtoV2() {
	}

	public UserCreationDtoV2(String name, Integer age, String email, String active) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
