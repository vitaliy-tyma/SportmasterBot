package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.util.HtmlReader;
import com.pioneersoft.sportmasterbot.util.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class StartController {

    @GetMapping
    public ResponseEntity<String> getBasePageWithAuthorization() {

        LogManager.writeLogText("Request to StartController method GET");
        String html = HtmlReader.readFromFile("user-input.html");

        return new ResponseEntity<String>(html, HttpStatus.OK);
    }
}
