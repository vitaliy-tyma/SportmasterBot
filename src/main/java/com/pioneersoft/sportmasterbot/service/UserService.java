package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.User;
import org.jsoup.Connection;

public interface UserService {

    Connection.Response tryToLogin(String login, String pass);

    User getUserInfo(Connection.Response userResponse);

}
