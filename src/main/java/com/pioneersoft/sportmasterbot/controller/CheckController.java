package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.util.HtmlReader;
import com.pioneersoft.sportmasterbot.util.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/check")
public class CheckController {

    @PostMapping(path = "/user")
    public ResponseEntity<String> checkUserAsRegistered(@RequestBody String login, @RequestBody String password) {
        LogManager.writeLogText("Request to ItemCheckController method POST");
        LogManager.writeLogText("Try to check user " + login + " with password " + password);

        // TODO check user
        String html = HtmlReader.readFromFile("shop-selection.html");
        return new ResponseEntity<String>(html, HttpStatus.OK);
    }

    @PostMapping(path = "/item")
    public ResponseEntity<String> findItem(@RequestBody String itemId) {
        LogManager.writeLogText("Request to UserController method POST");
        LogManager.writeLogText("Try to get item info with id " + itemId);

        // TODO check item
        String html = HtmlReader.readFromFile("shop-selection.html");
        return new ResponseEntity<String>(html, HttpStatus.OK);
    }

    @PostMapping(path = "/filter")
    public ResponseEntity<String> filterShops(@RequestBody String itemId, @RequestBody String filter) {
        LogManager.writeLogText("Request to UserController method POST");
        LogManager.writeLogText("Try to get shops with filter " + filter + " to get item " + itemId);

        // TODO filter shops
        String html = HtmlReader.readFromFile("shop-selection.html");
        return new ResponseEntity<String>(html, HttpStatus.OK);
    }
}
