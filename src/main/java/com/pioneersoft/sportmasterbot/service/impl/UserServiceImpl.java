package com.pioneersoft.sportmasterbot.service.impl;

import com.pioneersoft.sportmasterbot.dao.UserDAO;
import com.pioneersoft.sportmasterbot.model.User;
import com.pioneersoft.sportmasterbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public User findOne(String login) {
        return userDAO.findOne(login);
    }

    public User save(User user) {
        if (userDAO.findOne(user.getLogin()) == null) {
            return userDAO.save(user);
        }
        return null;
    }

    public User update(User user) {
        if (userDAO.findOne(user.getLogin()) != null) {
            return userDAO.save(user);
        }
        return null;
    }

    public void delete(String login) {
        userDAO.delete(login);
    }
}
