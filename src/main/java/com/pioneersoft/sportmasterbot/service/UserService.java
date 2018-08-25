package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.User;

public interface UserService {

    User findOne(String login);

    User save(User user);

    User update(User user);

    void delete(String login);
}
