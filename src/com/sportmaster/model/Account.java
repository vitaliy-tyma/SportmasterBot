package com.sportmaster.model;

public class Account {
	private String login;
	private String password;
	private String userName;
	
	public Account(String login, String password, String userName) {
		super();
		this.login = login;
		this.password = password;
		this.userName = userName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
