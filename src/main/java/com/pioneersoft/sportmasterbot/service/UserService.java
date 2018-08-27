package com.pioneersoft.sportmasterbot.service;

import com.pioneersoft.sportmasterbot.model.User;
import org.jsoup.nodes.Document;

public interface UserService {

    Document getDocumentWithLoginedUser(User user);

    void logoutUser(Document document);

    Document getDocumentWithRegisteredUser(User user);

    void deleteUserAccount(Document document);

}
