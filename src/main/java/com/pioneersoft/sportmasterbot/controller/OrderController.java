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
@RequestMapping(path = "/order")
public class OrderController {

    @PostMapping
    public ResponseEntity<String> makeOrder(@RequestBody String itemId, @RequestBody String shopId) {
        LogManager.writeLogText("Request to UserController method POST");
        LogManager.writeLogText("Try to order item " + itemId + "in shop " + shopId);

        // TODO make order
        String html = HtmlReader.readFromFile("order-info.html");
        return new ResponseEntity<String>(html, HttpStatus.OK);
    }

}
