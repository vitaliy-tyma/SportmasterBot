package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.util.HtmlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping(path = "/")
public class StartController {

    private static Logger logger = Logger.getLogger(StartController.class.getName());

    @Autowired
    HtmlManager htmlManager;

    @GetMapping
    public ResponseEntity<String> getBasePageWithAuthorization() {

        logger.info("Request to StartController method GET");
        String html = htmlManager.readFromFile("user-input.html");

        return new ResponseEntity<String>(html, HttpStatus.OK);
    }
}
