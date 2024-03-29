package com.example.customjsonserialization.example1;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "email", "login" })
public class User1 implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("user_id")
	private Long id;
	@JsonProperty("user_login")
	private String login;
	@JsonProperty("user_email")
	private String email;
	@JsonIgnore
	private String password;

	public User1() {
	}

	public User1(Long id, String login, String email, String password) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
