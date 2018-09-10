package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.util.HtmlManager;
import com.pioneersoft.sportmasterbot.util.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    HtmlManager htmlManager;

    @PostMapping
    public ResponseEntity<String> makeOrder
            (@RequestParam String itemId, @RequestParam String shopId, @RequestParam String login,@RequestParam String password) {
        LogManager.writeLogText("Request to UserController method POST");
        LogManager.writeLogText("Try to order item " + itemId + " in shop " + shopId + " for account " + login);


        String html = htmlManager.readFromFile("order-info.html");
        html = String.format(html, login, password);

        return new ResponseEntity<String>(html, HttpStatus.OK);
    }

}
