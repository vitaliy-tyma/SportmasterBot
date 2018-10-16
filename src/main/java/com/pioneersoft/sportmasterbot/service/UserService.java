package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.User;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public interface UserService {

    Connection.Response tryToLogin(String login, String pass);

    User getUserInfo(String jsonContent);


}
