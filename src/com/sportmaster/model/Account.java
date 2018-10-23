package com.sportmaster.model;

public class Account {
	private String login;
	private String password;
	private String user_name;
	
	public Account(String login, String password, String user_name) {
		super();
		this.login = login;
		this.password = password;
		this.user_name = user_name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	
}
