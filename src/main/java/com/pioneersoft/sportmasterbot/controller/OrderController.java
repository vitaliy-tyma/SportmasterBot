package com.pioneersoft.sportmasterbot.controller;

import com.pioneersoft.sportmasterbot.model.Order;
import com.pioneersoft.sportmasterbot.service.OrderService;
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
    
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<String> makeOrder
            (@RequestParam String itemId, @RequestParam String shopId, @RequestParam String login,@RequestParam String password) {
        LogManager.writeLogText("Request to UserController method POST");
        LogManager.writeLogText("Try to order item " + itemId + " in shop " + shopId + " for account " + login);

        Order order = orderService.makeOrder(itemId, shopId, login, password);

        String html = htmlManager.getOrderPage(order);

        return new ResponseEntity<>(html, HttpStatus.OK);
    }

}
