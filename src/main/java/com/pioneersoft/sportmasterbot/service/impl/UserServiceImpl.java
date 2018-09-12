package com.pioneersoft.sportmasterbot.service.impl;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.pioneersoft.sportmasterbot.model.User;
import com.pioneersoft.sportmasterbot.service.UserService;
import com.pioneersoft.sportmasterbot.util.Timer;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    public Connection.Response tryToLogin(String login, String pass) {

        Connection.Response initResponse = Jsoup.connect("https://www.sportmaster.ru/user/session/login.do").response();

        Map<String, String> cookies = initResponse.cookies();

        Timer.waitSeconds(1);

        Connection connection = Jsoup.connect("https://www.sportmaster.ru/user/session/login.do?continue=%2F");

        connection.header("accept", " text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        connection.header("accept-encoding", " gzip, deflate, br");
        connection.header("accept-language", " ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,uk;q=0.6,de;q=0.5");
        connection.header("origin", " https://www.sportmaster.ru");
        connection.header("referer", " https://www.sportmaster.ru/user/session/login.do");
        connection.header("upgrade-insecure-requests", "1");

        connection.cookies(cookies);

        connection.data("email", login);
        connection.data("password", pass);
        connection.data("option", "email");

        try {
            Connection.Response response = connection.ignoreContentType(true).method(Connection.Method.POST).execute();
            Map<String, String> userCookies = response.cookies();
            if (!userCookies.isEmpty() && userCookies.keySet().contains("userId")) {
                return response;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserInfo(Connection.Response userResponse) {

        User user = null;
        if (userResponse != null) {
            try {
                Document document = userResponse.parse();

                String jsonInHtml = document.getElementsByTag("sm-delivery-page").first().attr("params");
                jsonInHtml = StringUtils.substringBetween(jsonInHtml, "json:", "orderEditingSession:");
                jsonInHtml = StringUtils.substringBeforeLast(jsonInHtml, ",");

                DocumentContext context = JsonPath.parse(jsonInHtml);
                user.setEmail(context.read("$.contacts.email", String.class));
                user.setName(context.read("$.contacts.name", String.class));
                user.setPhone(context.read("$.contacts.phone", String.class));

                user.setUserWebId(userResponse.cookies().get("userId"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
