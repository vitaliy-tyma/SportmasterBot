package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.util.HtmlManager;
import com.pioneersoft.sportmasterbot.util.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class StartController {

    @Autowired
    HtmlManager htmlManager;

    @GetMapping
    public ResponseEntity<String> getBasePageWithAuthorization() {

        LogManager.writeLogText("Request to StartController method GET");
        String html = htmlManager.readFromFile("user-input.html");

        return new ResponseEntity<String>(html, HttpStatus.OK);
    }
}
