package com.example.customjsonserialization.example2;

import java.io.Serializable;

public class User2 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	private String email;
	private String password;

	public User2() {
	}

	public User2(Long id, String login, String email, String password) {
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
