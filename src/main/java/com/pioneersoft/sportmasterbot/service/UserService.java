package com.pioneersoft.sportmasterbot.service;

import org.jsoup.Connection;

public interface UserService {

    Connection.Response tryToLogin(String login, String pass);

}
